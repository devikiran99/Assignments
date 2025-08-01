package com.devikiran.assignments.data

import com.google.gson.annotations.SerializedName

data class GHRepoResponse(
    @SerializedName("items")
    val ghRepoDataList: List<GHRepoData>
)