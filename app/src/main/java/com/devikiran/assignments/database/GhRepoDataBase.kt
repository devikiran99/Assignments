package com.devikiran.assignments.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devikiran.assignments.data.GHRepoData

@Database(entities = [GHRepoData::class], version = 1)
abstract class GhRepoDataBase: RoomDatabase() {
    abstract fun GhRepoDao(): GhRepoDao
}