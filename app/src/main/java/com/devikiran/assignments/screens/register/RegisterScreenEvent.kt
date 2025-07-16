package com.devikiran.assignments.screens.register

sealed class RegisterScreenEvent {
    data class OnEmail(val email: String): RegisterScreenEvent()
    data class OnEnterPassword(val password: String): RegisterScreenEvent()
    data class OnReEnterPassword(val password: String): RegisterScreenEvent()
    data object OnRegister : RegisterScreenEvent()
}