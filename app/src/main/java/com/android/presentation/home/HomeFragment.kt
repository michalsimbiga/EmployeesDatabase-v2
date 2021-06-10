package com.android.presentation.home

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.android.di.home.HomeInjector
import com.core.ui.BaseFragment
import com.employeedatabase.R
import com.employeedatabase.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragment(override val layoutId: Int = R.layout.fragment_home) :
    BaseFragment<FragmentHomeBinding>() {

    private val injector: HomeInjector
        get() = baseActivity.application as HomeInjector

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    @Inject
    lateinit var navigator: HomeNavigator

    private val fragmentViewModel: HomeViewModel by viewModels { viewModelProviderFactory }
    private val adapter: EmployeesAdapter by lazy {
        EmployeesAdapter(
            onDeleteClick = { employee -> fragmentViewModel.deleteEmployee(employee = employee) },
            onEditClick = { employee -> navigator.navigateToAddFragmentWithEmployee(employee) }
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupListeners()
        setupObservers()
    }

    private fun setupView() {
        binding.rvEmployees.adapter = adapter
    }

    private fun setupListeners() = with(binding) {
        addButton.setOnClickListener { navigator.navigateToAddFragment() }
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            fragmentViewModel.uiState.collect { adapter.submitList(it) }
        }
    }

    override fun onDestroyView() {
        binding.rvEmployees.adapter = null
        super.onDestroyView()
    }

}
