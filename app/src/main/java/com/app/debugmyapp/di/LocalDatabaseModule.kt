package com.app.debugmyapp.di

import android.content.Context
import androidx.room.Room
import com.app.debugmyapp.repo.local.LocalDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): LocalDataBase {
        return Room.databaseBuilder(
            appContext,
            LocalDataBase::class.java,
            "local_database"
        ).build()
    }
}