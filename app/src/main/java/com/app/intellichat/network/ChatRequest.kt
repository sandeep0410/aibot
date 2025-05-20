package com.app.intellichat.network

import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

// Request data class for OpenAI API
data class ChatRequest(
    val model: String = "text-davinci-003",
    val prompt: String,
    val max_tokens: Int = 100
)

// Response data class for OpenAI API
data class ChatResponse(
    val choices: List<Choice>
) {
    data class Choice(
        val text: String
    )
}

// Retrofit API service interface
interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("v1/completions")
    suspend fun getChatCompletion(@Body request: ChatRequest): ChatResponse
}