package com.app.intellichat.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.app.intellichat.ui.components.MessageBubble
import com.app.intellichat.ui.components.TypingIndicator
import com.app.intellichat.Repository

@Composable
fun ChatScreen(repository: Repository) {
    var messages by remember { mutableStateOf(listOf("Hello! How can I assist you today?")) }
    var inputText by remember { mutableStateOf(TextFieldValue("")) }
    var isTyping by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Chat messages
        Column(modifier = Modifier.weight(1f)) {
            messages.forEach { message ->
                MessageBubble(message)
            }
            if (isTyping) {
                TypingIndicator()
            }
        }

        // Input field
        Row(modifier = Modifier.fillMaxWidth()) {
            BasicTextField(
                value = inputText,
                onValueChange = { inputText = it },
                modifier = Modifier.weight(1f).padding(8.dp)
            )
            Button(onClick = {
                val userMessage = inputText.text
                messages = messages + userMessage
                inputText = TextFieldValue("")
                isTyping = true

                // Fetch response from API
                repository.getChatResponse(userMessage) { response ->
                    messages = messages + response
                    isTyping = false
                }
            }) {
                Text("Send")
            }
        }
    }
}