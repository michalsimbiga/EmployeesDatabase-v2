package com.prosoma.livingwell.di.first

import androidx.fragment.app.Fragment
import com.prosoma.livingwell.di.FragmentScope
import com.android.presentation.first.FirstFragment
import dagger.BindsInstance
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [FirstModule::class])
interface FirstComponent {

    fun inject(fragment: FirstFragment)

    @Subcomponent.Factory
    interface Factory {

        fun bindFragment(@BindsInstance fragment: Fragment): FirstComponent
    }
}