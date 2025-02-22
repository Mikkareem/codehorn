package com.techullurgy.codehorn.gateway.request

data class AuthLoginResponse(
    val status: String,
    val token: String? = null,
    val error: String? = null,
)
