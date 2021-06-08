package com.android.presentation.second

import android.content.Context
import com.core.ui.BaseFragment
import com.domain.models.MainNavigator
import com.prosoma.livingwell.R
import com.prosoma.livingwell.databinding.FragmentSecondBinding
import com.prosoma.livingwell.di.main.MainInjector
import javax.inject.Inject

class SecondFragment(override val layoutId: Int = R.layout.fragment_second) :
    BaseFragment<FragmentSecondBinding>() {

    private val injector: MainInjector
        get() = baseActivity.application as MainInjector

    @Inject
    lateinit var mainNavigator: MainNavigator

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
    }
}