package com.app.debugmyapp.di

import com.app.debugmyapp.repo.MyRepository
import com.app.debugmyapp.repo.MyRepositoryImpl
import com.app.debugmyapp.repo.local.LocalRepository
import com.app.debugmyapp.repo.local.LocalRepositoryImpl
import com.app.debugmyapp.repo.network.NetworkRepository
import com.app.debugmyapp.repo.network.NetworkRepositoryImpl
import com.app.debugmyapp.ui.recipe.categorylist.MockDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(value = [SingletonComponent::class, FragmentComponent::class])
abstract class AppModule {

    @Binds
    abstract fun bindMyRepository(myRepository: MyRepositoryImpl): MyRepository

    @Binds
    abstract fun bindNetworkRepository(networkRepository : NetworkRepositoryImpl) :  NetworkRepository

    @Binds
    abstract fun bindLocalRepository(localRepository : LocalRepositoryImpl) :  LocalRepository


}
