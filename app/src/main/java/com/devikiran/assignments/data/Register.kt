package com.devikiran.assignments.data

data class RegisterRequest(
    val email: String,
    val password: String
)

data class RegisterResponse(
    val email: String,
    val hashedPassword: String,
    val id: Int
)