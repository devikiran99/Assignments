package com.devikiran.assignments.network

import com.devikiran.assignments.data.NoteData
import com.devikiran.assignments.data.RefreshRequest
import com.devikiran.assignments.data.Request
import com.devikiran.assignments.data.TokenPair
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("/auth/register")
    suspend fun register(@Body request: Request): Response<Void>

    @POST("/auth/login")
    suspend fun login(@Body request: Request): Response<TokenPair>

    @POST("/notes")
    suspend fun addNote(
        @Header("Authorization") token: String,
        @Body note: NoteData): Response<Void>

    @POST("/auth/refresh")
    suspend fun refreshToken(
        @Body refreshRequest: RefreshRequest
    ): Response<TokenPair>
}