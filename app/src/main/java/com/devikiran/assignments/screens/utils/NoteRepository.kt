package com.devikiran.assignments.screens.utils

import com.devikiran.assignments.data.NoteData
import com.devikiran.assignments.data.RefreshRequest
import com.devikiran.assignments.data.Request
import com.devikiran.assignments.data.TokenPair
import com.devikiran.assignments.network.ApiService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun registerUser(request: Request, onProcessingEvent: (NoteProgressEvent<String>) -> Unit) = withContext(IO) {
        try {
            onProcessingEvent(NoteProgressEvent.Start)
            val response = apiService.register(request)
            if (response.isSuccessful) {
                onProcessingEvent(NoteProgressEvent.Success(response.code().toString()))
            } else {
                onProcessingEvent(NoteProgressEvent.Fail(response.errorBody().toString()))
            }
        } catch (e: Exception) {
            onProcessingEvent(NoteProgressEvent.Fail(e.message.toString()))
        } finally {
            delay(100)
            onProcessingEvent(NoteProgressEvent.Completed)
        }
    }

    suspend fun loginUser(request: Request, onProcessingEvent: (NoteProgressEvent<TokenPair>) -> Unit) = withContext(IO) {
        try {
            onProcessingEvent(NoteProgressEvent.Start)
            val response = apiService.login(request)
            if (response.isSuccessful) {
                onProcessingEvent(NoteProgressEvent.Success(response.body()!!))
            } else {
                onProcessingEvent(NoteProgressEvent.Fail(response.errorBody().toString()))
            }
        } catch (e: Exception) {
            onProcessingEvent(NoteProgressEvent.Fail(e.message.toString()))
        } finally {
            delay(100)
            onProcessingEvent(NoteProgressEvent.Completed)
        }
    }

    suspend fun addNote(noteData: NoteData, onProcessingEvent: (NoteProgressEvent<Void>) -> Unit) = withContext(IO) {
        try {
            val token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2ODc1MTUwNTQ3ODVjYjY3NzJhNTczMDgiLCJ0eXBlIjoiYWNjZXNzIiwiaWF0IjoxNzUyNTA2NDQ3LCJleHAiOjE3NTI1MDczNDd9.EBEroit7u8Zg8lpS4SRKMqYlzHfPSciQdXSaj07vkEM"
            val response = apiService.addNote(token, noteData)
            if (response.isSuccessful) {
                onProcessingEvent(NoteProgressEvent.Success(response.body()!!))
            } else {
                onProcessingEvent(NoteProgressEvent.Fail(response.errorBody().toString()))
            }
        } catch (e: Exception) {
            onProcessingEvent(NoteProgressEvent.Fail(e.message.toString()))
        } finally {
            delay(100)
            onProcessingEvent(NoteProgressEvent.Completed)
        }
    }

    suspend fun refreshToken(onProcessingEvent: (NoteProgressEvent<TokenPair>) -> Unit) = withContext(IO) {
        try {
            val token = RefreshRequest(
                refreshToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2ODc1MTUwNTQ3ODVjYjY3NzJhNTczMDgiLCJ0eXBlIjoicmVmcmVzaCIsImlhdCI6MTc1MjUwNjQ0NywiZXhwIjoxNzU1MDk4NDQ3fQ.6Iz9zliAt-x5jL_hAqKFcC3mwIkLHDRdrag6XYpJ9us"
            )
            val response = apiService.refreshToken(token)
            if (response.isSuccessful) {
                onProcessingEvent(NoteProgressEvent.Success(response.body()!!))
            } else {
                onProcessingEvent(NoteProgressEvent.Fail(response.errorBody().toString()))
            }
        } catch (e: Exception) {
            onProcessingEvent(NoteProgressEvent.Fail(e.message.toString()))
        } finally {
            delay(100)
            onProcessingEvent(NoteProgressEvent.Completed)
        }
    }
}