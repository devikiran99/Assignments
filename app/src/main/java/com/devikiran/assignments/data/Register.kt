package com.devikiran.assignments.data

data class Request(
    val email: String,
    val password: String
)

data class TokenPair(
    val accessToken: String,
    val refreshToken: String
)

data class RefreshRequest(
    val refreshToken: String
)