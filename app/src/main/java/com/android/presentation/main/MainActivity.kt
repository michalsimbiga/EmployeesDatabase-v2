package com.android.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.core.ui.BaseActivity
import com.domain.models.MainNavigator
import com.prosoma.livingwell.R
import com.prosoma.livingwell.databinding.ActivityMainBinding
import com.prosoma.livingwell.di.main.MainInjector
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val injector: MainInjector
        get() = application as MainInjector

    override val layoutId: Int = R.layout.activity_main

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