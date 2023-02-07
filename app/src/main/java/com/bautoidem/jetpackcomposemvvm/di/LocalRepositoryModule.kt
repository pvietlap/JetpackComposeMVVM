package com.bautoidem.jetpackcomposemvvm.di

import android.content.Context
import androidx.room.Room
import com.bautoidem.jetpackcomposemvvm.data.LocalDatabase
import com.bautoidem.jetpackcomposemvvm.data.model.user_input.UserInputDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalRepositoryModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): LocalDatabase {
        return Room.databaseBuilder(context, LocalDatabase::class.java, "user_input.database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun userInputDap(database: LocalDatabase): UserInputDAO {
        return database.userInputDAO()
    }
}