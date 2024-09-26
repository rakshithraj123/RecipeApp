package com.app.debugmyapp.di

import com.app.debugmyapp.repo.network.LoggingInterceptor
import com.app.debugmyapp.repo.network.RetrofitApiService
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
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkkHttpClient() : OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(LoggingInterceptor())
            .connectTimeout(30L, TimeUnit.SECONDS) //Backend is really slow
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient : OkHttpClient) : Retrofit{
        return Retrofit.Builder().
                baseUrl("https://www.themealdb.com//api/json/v1/1/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
               .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitApiService(retrofit: Retrofit) : RetrofitApiService{
        return retrofit.create(RetrofitApiService::class.java)
    }
}