package com.android.presentation.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.android.di.home.HomeInjector
import com.core.ui.BaseFragment
import com.prosoma.livingwell.R
import com.prosoma.livingwell.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.view.*
import javax.inject.Inject

class HomeFragment(override val layoutId: Int = R.layout.fragment_home) :
    BaseFragment<FragmentHomeBinding>() {

    private val injector: HomeInjector
        get() = baseActivity.application as HomeInjector

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    private val viewModel: HomeViewModel by viewModels { viewModelProviderFactory }
    private val adapter: EmployeesAdapter by lazy { EmployeesAdapter() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
//        setupListeners()
//        setupRecyclerView()
//        setupViewLiveDataObservers()
    }


    override fun onDestroyView() {
//        onRecyclerViewDetached(homeRecycler)
//        addButton.setOnClickListener(null)

        super.onDestroyView()
    }
}
