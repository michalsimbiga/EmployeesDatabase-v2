package com.android.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.core.ui.BaseActivity
import com.domain.models.MainNavigator
import com.android.di.main.MainInjector
import com.employeedatabase.R
import com.employeedatabase.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity(override val layoutId: Int = R.layout.activity_main) :
    BaseActivity<ActivityMainBinding>() {

    private val injector: MainInjector
        get() = application as MainInjector

    @Inject
    lateinit var navigator: MainNavigator

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    private val viewModel: MainViewModel by viewModels { viewModelProviderFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        injector.clearMainComponent()
        super.onDestroy()
    }
}