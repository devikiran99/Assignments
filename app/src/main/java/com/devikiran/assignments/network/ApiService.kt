package com.devikiran.assignments.network

import com.devikiran.assignments.data.GHRepoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search/repositories")
    suspend fun getGhRepoData(
        @Query("q") query: String = "language:swift",
        @Query("sort") sort: String = "stars",
        @Query("order") order: String = "desc"
    ): GHRepoResponse
}