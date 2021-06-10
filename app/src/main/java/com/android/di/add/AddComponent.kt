package com.android.di.add

import androidx.fragment.app.Fragment
import com.android.di.FragmentScope
import com.android.presentation.edit.EditFragment
import dagger.BindsInstance
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [AddModule::class])
interface AddComponent {

    fun inject(fragment: EditFragment)

    @Subcomponent.Factory
    interface Factory {

        fun bindFragment(@BindsInstance fragment: Fragment): AddComponent
    }
}