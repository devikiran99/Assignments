package com.devikiran.assignments.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.devikiran.assignments.database.AppDataBase
import com.devikiran.assignments.network.ApiService
import com.devikiran.assignments.utils.Utils.BASE_URL
import com.devikiran.assignments.utils.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDataBase(app: Application): AppDataBase =
        Room.databaseBuilder(app, AppDataBase::class.java, "app_database")
            .build()

    @Provides
    @Singleton
    fun provideRepository(
        @ApplicationContext context: Context,
        apiService: ApiService,
        appDataBase: AppDataBase
        ) = MainRepository(context, apiService, appDataBase)

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesAppDAo(db: AppDataBase) = db.AppDao()
}