package com.app.intellichat

import com.app.intellichat.network.ApiService
import com.app.intellichat.network.ChatRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository {
    private val apiService: ApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openai.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    fun getChatResponse(prompt: String, callback: (String) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getChatCompletion(ChatRequest(prompt = prompt))
                val reply = response.choices.firstOrNull()?.text ?: "No response"
                callback(reply)
            } catch (e: Exception) {
                callback("Error: ${e.message}")
            }
        }
    }
}