package com.app.intellichat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.app.intellichat.ui.ChatScreen
import com.app.intellichat.ui.theme.IntelliChatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntelliChatTheme {
                ChatScreen(repository = Repository())
            }
        }
    }
}