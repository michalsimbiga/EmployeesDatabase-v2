package com.android.di.home

import androidx.fragment.app.Fragment
import com.android.presentation.home.HomeFragment
import com.android.di.FragmentScope
import dagger.BindsInstance
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [HomeModule::class])
interface HomeComponent {

    fun inject(fragment: HomeFragment)

    @Subcomponent.Factory
    interface Factory {

        fun bindFragment(@BindsInstance fragment: Fragment): HomeComponent
    }
}