package com.devikiran.assignments.screens.utils

import com.devikiran.assignments.data.NoteData
import com.devikiran.assignments.data.RefreshRequest
import com.devikiran.assignments.data.Request
import com.devikiran.assignments.network.ApiService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun registerUser() = withContext(IO) {
        val request = Request(
            email = "devikiranshettypn4@gmail.com",
            password = "TestDevi123"
        )

        try {
            val response = apiService.register(request)
            if (response.isSuccessful) {
                println("ssss Success: ${response.code()}")
            } else {
                println("ssss Error: ${response.errorBody()} ")
            }
        } catch (e: Exception) {
            println("ssss Network failure: ${e.message}")
        }
    }

    suspend fun loginUser() = withContext(IO) {
        val request = Request(
            email = "devikiranshettypn4@gmail.com",
            password = "TestDevi123"
        )

        try {
            val response = apiService.login(request)
            if (response.isSuccessful) {
                println("ssss Success: ${response.body()}")
            } else {
                println("ssss Error: ${response.errorBody()} ")
            }
        } catch (e: Exception) {
            println("ssss Network failure: ${e.message}")
        }
    }

    suspend fun addNote() = withContext(IO) {
        val noteData = NoteData(
            title = "Demo1",
            content = "Demo content1",
            color = 123355
        )

        try {
            val token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2ODc1MTUwNTQ3ODVjYjY3NzJhNTczMDgiLCJ0eXBlIjoiYWNjZXNzIiwiaWF0IjoxNzUyNTA2NDQ3LCJleHAiOjE3NTI1MDczNDd9.EBEroit7u8Zg8lpS4SRKMqYlzHfPSciQdXSaj07vkEM"
            val response = apiService.addNote(token, noteData)
            if (response.isSuccessful) {
                println("ssss Success: ${response.body()}")
            } else {
                println("ssss Error: ${response.errorBody()} ")
            }
        } catch (e: Exception) {
            println("ssss Network failure: ${e.message}")
        }
    }

    suspend fun refreshToken() = withContext(IO) {
        try {
            val token = RefreshRequest(
                refreshToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2ODc1MTUwNTQ3ODVjYjY3NzJhNTczMDgiLCJ0eXBlIjoicmVmcmVzaCIsImlhdCI6MTc1MjUwNjQ0NywiZXhwIjoxNzU1MDk4NDQ3fQ.6Iz9zliAt-x5jL_hAqKFcC3mwIkLHDRdrag6XYpJ9us"
            )
            val response = apiService.refreshToken(token)
            if (response.isSuccessful) {
                println("ssss Success: ${response.body()}")
            } else {
                println("ssss Error: ${response.errorBody()} ")
            }
        } catch (e: Exception) {
            println("ssss Network failure: ${e.message}")
        }
    }

}