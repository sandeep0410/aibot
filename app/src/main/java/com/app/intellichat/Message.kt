package com.app.intellichat

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

data class Message(
    val text: String,
    val isUser: Boolean // True if the message is from the user, false if from AI
)

class MainViewModel : ViewModel() {
    private val repository = Repository() // Initialize your repository

    val messages = mutableStateListOf<Message>()
    var currentInput = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    fun onInputChange(newInput: String) {
        currentInput.value = newInput
    }

    fun sendMessage() {
        val userMessageText = currentInput.value.trim()
        if (userMessageText.isNotEmpty()) {
            val userMessage = Message(userMessageText, true)
            messages.add(userMessage)
            currentInput.value = "" // Clear input field
            isLoading.value = true

            viewModelScope.launch {
                try {
                    repository.getChatResponse(prompt = userMessageText) { response ->
                        val botMessage = Message(response, false)
                        messages.add(botMessage)
                    }
                } catch (e: Exception) {
                    // Handle network errors or API errors
                    messages.add(Message("Error: Could not get response.", false))
                    // Log the error: Log.e("ChatViewModel", "API Error", e)
                } finally {
                    isLoading.value = false
                }
            }
        }
    }
}