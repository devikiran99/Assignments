package com.devikiran.assignments.screens.register

import com.devikiran.assignments.data.Request
import com.devikiran.assignments.screens.utils.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    repository: NoteRepository
){

    private val _registerRequestState = MutableStateFlow<Request?>(null)
    val registerRequestState: StateFlow<Request?> = _registerRequestState



}