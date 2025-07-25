package com.devikiran.assignments.data

import androidx.annotation.Keep
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "app_data")
data class AppData(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    @Embedded val rating: Rating
)

data class Rating(
    val rate: Double,
    val count: Int
)