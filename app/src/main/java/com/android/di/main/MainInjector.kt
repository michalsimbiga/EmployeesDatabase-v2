package com.prosoma.livingwell.di.main

import com.android.presentation.main.MainActivity
import com.android.presentation.second.SecondFragment

interface MainInjector {

    fun inject(activity: MainActivity)
    fun inject(fragment: SecondFragment)
    fun clearMainComponent()
}