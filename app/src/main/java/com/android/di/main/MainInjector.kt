package com.android.di.main

import com.android.presentation.main.MainActivity

interface MainInjector {

    fun inject(activity: MainActivity)
    fun clearMainComponent()
}