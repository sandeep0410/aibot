package com.app.intellichat.network

import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("v1/completions") // Replace with the actual endpoint
    suspend fun getChatCompletion(@Body request: ChatRequest): ChatResponse
}