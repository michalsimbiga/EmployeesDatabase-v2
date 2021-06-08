package com.android.di.add

import androidx.fragment.app.Fragment
import com.android.di.FragmentScope
import com.android.presentation.add.AddFragment
import dagger.BindsInstance
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [AddModule::class])
interface AddComponent {

    fun inject(fragment: AddFragment)

    @Subcomponent.Factory
    interface Factory {

        fun bindFragment(@BindsInstance fragment: Fragment): AddComponent
    }
}