package com.app.intellichat.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.intellichat.ChatViewModel
import com.app.intellichat.ChatViewModelFactory
import com.app.intellichat.Repository

@Composable
fun ChatScreen(viewModel: ChatViewModel = viewModel(factory = ChatViewModelFactory(Repository()))) {
    val messages = viewModel.messages
    val isTyping = viewModel.isTyping
    var inputText by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Display chat messages
        Column(modifier = Modifier.weight(1f)) {
            messages.forEach { message ->
                Text(message)
            }
            if (isTyping) {
                Text("Typing...")
            }
        }

        // Input field and send button
        Row(modifier = Modifier.fillMaxWidth()) {
            BasicTextField(
                value = inputText,
                onValueChange = { inputText = it },
                modifier = Modifier.weight(1f).padding(8.dp)
            )
            Button(
                onClick = {
                    viewModel.sendMessage(inputText)
                    inputText = "" // Clear input field
                },
                enabled = inputText.isNotEmpty() // Enable button only if input is not empty
            ) {
                Text("Send")
            }
        }
    }
}