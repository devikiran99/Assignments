package com.devikiran.assignments.network

import com.devikiran.assignments.data.AppData
import retrofit2.http.GET

interface ApiService {

    @GET("products")
    suspend fun getAppData(): List<AppData>
}