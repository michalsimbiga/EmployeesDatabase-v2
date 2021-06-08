package com.core.di

import androidx.lifecycle.ViewModelProvider
import com.core.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
interface CoreModule {

    @Binds
    fun bindViewModelProviderFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}