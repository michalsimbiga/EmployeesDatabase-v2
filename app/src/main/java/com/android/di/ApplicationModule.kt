package com.android.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.data.database.ApplicationDatabase
import com.prosoma.livingwell.di.main.MainModule
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [MainModule::class])
interface ApplicationModule {

    @Binds
    fun bindAppContext(application: Application): Context

//    @Provides
//    fun getDatabase(application: Application): ApplicationDatabase = Room.databaseBuilder(
//        application,
//        ApplicationDatabase::class.java, "database-name"
//    ).build()
}