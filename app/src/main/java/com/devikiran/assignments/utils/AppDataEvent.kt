package com.devikiran.assignments.utils

import com.devikiran.assignments.data.AppData

sealed class AppDataEvent<out T> {
    data object OnInitialization: AppDataEvent<Nothing>()
    data object OnLoading: AppDataEvent<Nothing>()
    data class OnSuccess(val appDataList: List<AppData>): AppDataEvent<Nothing>()
    data class OnComplete(val success: String): AppDataEvent<Nothing>()
    data class OnFailure(val error: String): AppDataEvent<Nothing>()
}