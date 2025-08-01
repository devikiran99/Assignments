package com.devikiran.assignments.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.devikiran.assignments.data.GHRepoData
import kotlinx.coroutines.flow.Flow

@Dao
interface GhRepoDao {

    @Query("SELECT * FROM gh_repo_data")
    fun getGhRepoData(): Flow<List<GHRepoData>>


    @Upsert
    suspend fun insertGhRepoData(ghRepoDataList: List<GHRepoData>)
}