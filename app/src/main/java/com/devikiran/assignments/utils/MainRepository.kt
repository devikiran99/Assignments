package com.devikiran.assignments.utils

import com.devikiran.assignments.data.DishData
import com.devikiran.assignments.network.ApiService
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getDishes(): List<DishData> = apiService.getDishes()
}