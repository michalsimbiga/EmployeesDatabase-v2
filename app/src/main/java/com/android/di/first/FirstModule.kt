package com.prosoma.livingwell.di.first

import androidx.lifecycle.ViewModel
import com.core.viewmodels.ViewModelKey
import com.android.presentation.first.FirstViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface FirstModule {

    @Binds
    @IntoMap
    @ViewModelKey(FirstViewModel::class)
    fun bindViewModel(viewModel: FirstViewModel): ViewModel
}