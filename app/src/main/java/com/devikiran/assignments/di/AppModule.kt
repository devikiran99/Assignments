package com.devikiran.assignments.di

import com.devikiran.assignments.network.ApiService
import com.devikiran.assignments.screens.utils.NoteRepository
import com.devikiran.assignments.screens.utils.Util
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
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