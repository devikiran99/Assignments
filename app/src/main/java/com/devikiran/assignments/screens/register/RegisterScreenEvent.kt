package com.devikiran.assignments.screens.register

sealed class RegisterScreenEvent {
    data class OnUserName(val userName: String): RegisterScreenEvent()
    data class OnEmail(val email: String): RegisterScreenEvent()
    data class OnEnterPassword(val password: String): RegisterScreenEvent()
    data class OnReEnterPassword(val rePassword: String): RegisterScreenEvent()
    data object OnPasswordVisible : RegisterScreenEvent()
    data object OnRegister : RegisterScreenEvent()
}