package com.devikiran.assignments.di

import com.devikiran.assignments.network.ApiService
import com.devikiran.assignments.utils.NoteRepository
import com.devikiran.assignments.utils.Util
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService) =  NoteRepository(apiService)

    @Provides
    @Singleton
    fun provideApiService(): ApiService{
        return Retrofit.Builder()
            .baseUrl(Util.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}