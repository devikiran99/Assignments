package com.devikiran.assignments.network

import com.devikiran.assignments.data.DishData
import retrofit2.http.GET

interface ApiService {

    @GET("nosh-assignment")
    suspend fun getDishes(): List<DishData>
}