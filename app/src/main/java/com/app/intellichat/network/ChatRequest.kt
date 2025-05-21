package com.app.intellichat.network

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
