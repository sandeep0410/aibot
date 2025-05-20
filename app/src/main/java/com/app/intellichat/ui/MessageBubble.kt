package com.app.intellichat.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MessageBubble(message: String) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(4.dp).fillMaxWidth()
    ) {
        Text(
            text = message,
            modifier = Modifier.padding(8.dp),
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}