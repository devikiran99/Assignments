package com.devikiran.assignments.screens.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devikiran.assignments.data.LoginData
import com.devikiran.assignments.data.NoteData
import com.devikiran.assignments.data.NoteListScreenData
import com.devikiran.assignments.data.RegistrationData
import com.devikiran.assignments.data.RegistrationScreenData
import com.devikiran.assignments.data.Request
import com.devikiran.assignments.data.TokenPair
import com.devikiran.assignments.data.ValidToken
import com.devikiran.assignments.screens.utils.NoteProgressEvent
import com.devikiran.assignments.screens.utils.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    private val _registerRequestState =
        MutableStateFlow<RegistrationScreenData>(RegistrationScreenData())
    val registerRequestState: StateFlow<RegistrationScreenData> = _registerRequestState


    lateinit var navigateTo: (Any) -> Unit

    init {
        isTokenValid()
    }

    fun isTokenValid() {

        viewModelScope.launch {
            val isRefreshTokenValid = repository.isRefreshTokenValid(ValidToken(""))
            if (isRefreshTokenValid) {
                navigateTo(NoteListScreenData)
            } else {
                val isAccessTokenValid = repository.isAccessTokenValid(ValidToken(""))
                if (isAccessTokenValid) {
                    repository.refreshToken { tokenState ->
                        when (tokenState) {
                            NoteProgressEvent.Completed -> {}

                            is NoteProgressEvent.Fail -> {
                                navigateTo(RegistrationData)
                            }

                            NoteProgressEvent.Start, NoteProgressEvent.Progress -> {
                                navigateTo("LoadingScreen")
                            }

                            is NoteProgressEvent.Success<TokenPair> -> {
                                val token = tokenState.success
                                navigateTo(NoteListScreenData)
                            }
                        }
                    }
                } else {
                    navigateTo(LoginData)
                }
            }
        }
    }

    fun onValueChange(event: RegisterScreenEvent) {
        when (event) {
            is RegisterScreenEvent.OnUserName -> {
                viewModelScope.launch {
                    _registerRequestState.update { state ->
                        state.copy(userName = event.userName)
                    }
                }
            }

            is RegisterScreenEvent.OnEmail -> {
                viewModelScope.launch {
                    _registerRequestState.update { state ->
                        state.copy(email = event.email)

                    }
                }
            }

            is RegisterScreenEvent.OnEnterPassword -> {
                viewModelScope.launch {
                    _registerRequestState.update { state ->
                        state.copy(password = event.password)
                    }
                }
            }

            is RegisterScreenEvent.OnReEnterPassword -> {
                viewModelScope.launch {
                    _registerRequestState.update { state ->
                        val isRegister = state.password == event.rePassword
                        state.copy(
                            rePassword = event.rePassword,
                            isRegister = isRegister
                        )
                    }
                }
            }

            RegisterScreenEvent.OnPasswordVisible -> {
                viewModelScope.launch {
                    _registerRequestState.update { state ->
                        state.copy(isPasswordVisible = !state.isPasswordVisible)
                    }
                }
            }

            RegisterScreenEvent.OnRegister -> {
                viewModelScope.launch {
                    val registerData = _registerRequestState.value ?: return@launch
                    val register = Request(
                        email = registerData.email,
                        password = registerData.password
                    )
                    repository.registerUser(register) {
                        handleRegistration(it)
                    }
                }
            }
        }
    }

    private fun handleRegistration(processEvent: NoteProgressEvent<String>) {
        viewModelScope.launch {
            when (processEvent) {
                NoteProgressEvent.Completed -> {

                }

                is NoteProgressEvent.Fail -> {

                }

                NoteProgressEvent.Progress -> {

                }

                NoteProgressEvent.Start -> {

                }

                is NoteProgressEvent.Success<*> -> {

                }
            }
        }
    }

    private fun handleLogin(processEvent: NoteProgressEvent<String>) {
        viewModelScope.launch {
            when (processEvent) {
                NoteProgressEvent.Completed -> {

                }

                is NoteProgressEvent.Fail -> {

                }

                NoteProgressEvent.Progress -> {

                }

                NoteProgressEvent.Start -> {

                }

                is NoteProgressEvent.Success<*> -> {

                }
            }
        }
    }
}