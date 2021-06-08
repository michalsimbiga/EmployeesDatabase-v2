package com.android.presentation.add

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.android.di.add.AddInjector
import com.android.presentation.home.EmployeesAdapter
import com.core.ui.BaseFragment
import com.prosoma.livingwell.R
import com.prosoma.livingwell.databinding.FragmentEditBinding
import javax.inject.Inject

class AddFragment(override val layoutId: Int = R.layout.fragment_edit) :
    BaseFragment<FragmentEditBinding>() {

    private val injector: AddInjector
        get() = baseActivity.application as AddInjector

    @Inject
    lateinit var navigator: AddNavigator

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    private val viewModel: AddViewModel by viewModels { viewModelProviderFactory }
    private val adapter: EmployeesAdapter by lazy { EmployeesAdapter() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addEmployeeButton.setOnClickListener {
            navigator.navigateToHome()
        }
    }

}