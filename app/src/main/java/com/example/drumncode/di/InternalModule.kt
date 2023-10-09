package com.example.drumncode.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.drumncode.data.database.dao.PhotoDao
import com.example.drumncode.data.database.PhotoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InternalModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context =
        application.applicationContext

    @Provides
    @Singleton
    fun provideSavedNewsDatabase(context: Context): PhotoDatabase =
        Room.databaseBuilder(
            context,
            PhotoDatabase::class.java,
            PhotoDatabase.DATABASE_NAMESPACE
        ).build()

    @Singleton
    @Provides
    fun provideMoviesDao(moviesDatabase: PhotoDatabase): PhotoDao = moviesDatabase.photoListDao()

}
