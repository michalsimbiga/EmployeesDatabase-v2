package com.android.presentation.add

import android.content.Context
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.android.di.add.AddInjector
import com.android.di.home.HomeInjector
import com.android.presentation.home.EmployeesAdapter
import com.core.ui.BaseFragment
import com.prosoma.livingwell.R
import com.prosoma.livingwell.databinding.FragmentHomeBinding
import javax.inject.Inject

class AddFragment(override val layoutId: Int = R.layout.fragment_edit) :
    BaseFragment<FragmentHomeBinding>() {

    private val injector: AddInjector
        get() = baseActivity.application as AddInjector

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    private val viewModel: AddViewModel by viewModels { viewModelProviderFactory }
    private val adapter: EmployeesAdapter by lazy { EmployeesAdapter() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
    }

}