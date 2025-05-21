package com.app.intellichat

import com.app.intellichat.network.ApiService
import com.app.intellichat.network.ChatRequest
import com.app.intellichat.network.RetrofitProvider

class Repository(private val apiService: ApiService = RetrofitProvider.apiService) {
    suspend fun getChatResponse(prompt: String): String {
        return try {
            val response = apiService.getChatCompletion(ChatRequest(prompt = prompt))
            response.choices.firstOrNull()?.text ?: "No response"
        } catch (e: Exception) {
            "Error: ${e.message}"
        }
    }
}