package com.devikiran.assignments.utils

import android.content.Context
import com.devikiran.assignments.data.GHRepoData
import com.devikiran.assignments.database.GhRepoDataBase
import com.devikiran.assignments.network.ApiService
import com.devikiran.assignments.utils.Utils.isNetworkAvailable
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val context: Context,
    private val apiService: ApiService,
    ghRepoDb: GhRepoDataBase
) {
    private val ghRepoDao = ghRepoDb.GhRepoDao()

    fun getGhRepoData(): Flow<List<GHRepoData>> = ghRepoDao.getGhRepoData()

    suspend fun getGhRepoDataFromServer(
        onProcessingEvent: (AppDataEvent<String>) -> Unit
    ) = withContext(IO) {

        try {
            onProcessingEvent(AppDataEvent.OnLoading)
            if (isNetworkAvailable(context)) {
                val ghRepoDataList = withContext(IO) { apiService.getGhRepoData().ghRepoDataList }
                if (ghRepoDataList.isNotEmpty()) {
                    withContext(IO) { ghRepoDao.insertGhRepoData(ghRepoDataList) }
                    onProcessingEvent(AppDataEvent.OnComplete("Fetched Successfully"))
                }
            } else {
                onProcessingEvent(AppDataEvent.OnFailure("Internet not available}"))
            }
        } catch (e: Exception) {
            onProcessingEvent(AppDataEvent.OnFailure("Error Occurred ${e.message}"))
        }
    }
}