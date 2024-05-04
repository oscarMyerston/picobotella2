package com.example.picobotella.di

import android.content.Context
import com.example.picobotella.data.DBReto
import com.example.picobotella.data.DaoReto
import com.example.picobotella.service.ApiService
import com.example.picobotella.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class )
object Module {
    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: Context): DBReto {
        return DBReto.getDataBase(context)
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideDaoReto(dbReto: DBReto) : DaoReto {
        return dbReto.daoReto()
    }
}