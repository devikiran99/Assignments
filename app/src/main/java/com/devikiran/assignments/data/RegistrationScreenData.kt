package com.devikiran.assignments.data

data class RegistrationScreenData(
    val userName: String,
    val email: String,
    val password: String,
    val rePassword: String,
    val isRegister: Boolean,
    var isPasswordVisible: Boolean
)