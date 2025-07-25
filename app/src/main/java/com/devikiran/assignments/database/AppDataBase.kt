package com.devikiran.assignments.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devikiran.assignments.data.AppData

@Database(entities = [AppData::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun AppDao(): AppDao
}