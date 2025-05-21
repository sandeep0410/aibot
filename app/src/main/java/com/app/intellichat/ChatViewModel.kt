package com.app.intellichat

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ChatViewModel(private val repository: Repository) : ViewModel() {

    // State to hold chat messages
    private val _messages = mutableStateListOf<String>()
    val messages: List<String> get() = _messages

    // State to track if the app is "typing"
    private var _isTyping = mutableStateOf(false)
    val isTyping: Boolean get() = _isTyping.value

    // Function to send a message and fetch a response
    fun sendMessage(prompt: String) {
        _messages.add("You: $prompt") // Add user message to the list
        _isTyping.value = true // Show typing indicator

        viewModelScope.launch {
            val response = repository.getChatResponse(prompt)
            _messages.add("AI: $response") // Add AI response to the list
            _isTyping.value = false // Hide typing indicator
        }
    }
}