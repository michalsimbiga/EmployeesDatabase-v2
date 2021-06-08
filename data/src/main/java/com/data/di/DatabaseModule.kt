package com.data.di

import android.content.Context
import androidx.room.Room
import com.data.database.ApplicationDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun getDatabase(context: Context): ApplicationDatabase = Room.databaseBuilder(
        context,
        ApplicationDatabase::class.java, "database-name"
    ).build()
}