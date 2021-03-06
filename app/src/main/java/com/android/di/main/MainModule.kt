package com.android.di.main

import androidx.lifecycle.ViewModel
import com.android.presentation.main.MainActivityNavigator
import com.android.presentation.main.MainViewModel
import com.core.viewmodels.ViewModelKey
import com.domain.models.MainNavigator
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainModule {

    @Binds
    fun bindNavigator(navigator: MainActivityNavigator): MainNavigator

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindViewModel(viewModel: MainViewModel): ViewModel
}