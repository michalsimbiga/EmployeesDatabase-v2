package com.prosoma.livingwell.di

import android.app.Application
import android.content.Context
import com.prosoma.livingwell.di.main.MainModule
import dagger.Binds
import dagger.Module

@Module(includes = [MainModule::class])
interface ApplicationModule {

    @Binds
    fun bindAppContext(application: Application): Context
}