package com.data.di

import android.app.Application
import androidx.room.Room
import com.data.database.ApplicationDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule  {

    @Provides
    fun provideDatabase(application: Application) = Room.databaseBuilder(
        application.applicationContext,
        ApplicationDatabase::class.java, "Employees.db"
    ).build()
}