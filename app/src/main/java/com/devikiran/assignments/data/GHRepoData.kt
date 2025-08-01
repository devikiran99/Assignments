package com.devikiran.assignments.data

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Keep
@Entity(tableName = "gh_repo_data")
data class GHRepoData(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val name: String,
    @SerializedName("html_url")
    val htmlUrl: String
)

