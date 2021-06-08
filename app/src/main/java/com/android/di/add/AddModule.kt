package com.android.di.add

import androidx.lifecycle.ViewModel
import com.android.presentation.add.AddViewModel
import com.core.viewmodels.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AddModule {

    @Binds
    @IntoMap
    @ViewModelKey(AddViewModel::class)
    fun bindViewModel(viewModel: AddViewModel): ViewModel
}