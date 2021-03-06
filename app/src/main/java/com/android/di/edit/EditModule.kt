package com.android.di.edit

import androidx.lifecycle.ViewModel
import com.android.presentation.edit.EditViewModel
import com.core.viewmodels.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface EditModule {

    @Binds
    @IntoMap
    @ViewModelKey(EditViewModel::class)
    fun bindViewModel(viewModel: EditViewModel): ViewModel
}