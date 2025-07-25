package com.devikiran.assignments.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.devikiran.assignments.data.AppData
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    @Query("SELECT * FROM app_data")
    fun getAppData(): Flow<List<AppData>>


    @Upsert
    suspend fun insertAppData(appData: List<AppData>)
}