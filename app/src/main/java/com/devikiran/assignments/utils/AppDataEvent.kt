package com.devikiran.assignments.utils

import com.devikiran.assignments.data.GHRepoData

sealed class AppDataEvent<out T> {
    data object OnInitialization: AppDataEvent<Nothing>()
    data object OnLoading: AppDataEvent<Nothing>()
    data class OnSuccess(val GHRepoDataList: List<GHRepoData>): AppDataEvent<Nothing>()
    data class OnComplete(val success: String): AppDataEvent<Nothing>()
    data class OnFailure(val error: String): AppDataEvent<Nothing>()
}