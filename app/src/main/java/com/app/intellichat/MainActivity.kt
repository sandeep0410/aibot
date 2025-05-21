package com.app.intellichat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.intellichat.ui.ChatScreen
import com.app.intellichat.ui.theme.IntelliChatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntelliChatTheme {
                // Create a Repository instance
                val repository = Repository()

                // Use a ViewModelFactory to create the ChatViewModel
                val chatViewModel: ChatViewModel = viewModel(factory = ChatViewModelFactory(repository))

                // Pass the ViewModel to the ChatScreen
                ChatScreen(viewModel = chatViewModel)
            }
        }
    }
}