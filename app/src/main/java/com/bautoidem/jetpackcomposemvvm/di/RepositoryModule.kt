package com.bautoidem.jetpackcomposemvvm.di

import android.content.Context
import com.bautoidem.jetpackcomposemvvm.data.ApiService
import com.bautoidem.jetpackcomposemvvm.data.model.user_input.UserInputDAO
import com.bautoidem.jetpackcomposemvvm.data.repository.ApiRepositoryImpl
import com.bautoidem.jetpackcomposemvvm.data.repository.LocalRepositoryImpl
import com.bautoidem.jetpackcomposemvvm.domain.ApiRepository
import com.bautoidem.jetpackcomposemvvm.domain.LocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideApiRepository(
        @ApplicationContext appContext: Context,
        apiService: ApiService
    ): ApiRepository = ApiRepositoryImpl(context = appContext, apiService = apiService)

    @Provides
    @Singleton
    fun provideLocalRepository(
        @ApplicationContext appContext: Context,
        userInputDAO: UserInputDAO
    ): LocalRepository = LocalRepositoryImpl(context = appContext, userInputDAO = userInputDAO)
}