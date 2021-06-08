package com.android.presentation.first

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.core.ui.BaseFragment
import com.domain.models.MainNavigator
import com.prosoma.livingwell.R
import com.prosoma.livingwell.databinding.FragmentFirstBinding
import com.prosoma.livingwell.di.first.FirstInjector
import kotlinx.android.synthetic.main.fragment_first.*
import javax.inject.Inject

class FirstFragment(override val layoutId: Int = R.layout.fragment_first) :
    BaseFragment<FragmentFirstBinding>() {

    private val injector: FirstInjector
        get() = baseActivity.application as FirstInjector

    @Inject
    lateinit var mainNavigator: MainNavigator

    @Inject
    lateinit var navigator: FirstNavigator

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    private val viewModel: FirstViewModel by viewModels { viewModelProviderFactory }

    private val adapter: UsersAdapter by lazy { UsersAdapter() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        viewModel.getUsers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupView()
    }

    private fun setupView() {
        setupListeners()
        setupRecyclerView()
        setupViewLiveDataObservers()
    }

    private fun setupRecyclerView() {
        rvFirstUsers.adapter = adapter
    }

    private fun setupListeners() {
        buttonFirstShowDialog.setOnClickListener { mainNavigator.showFirstDialog() }
        buttonFirstNavigate.setOnClickListener { navigator.navigateToSecondFragment() }
    }


    private fun setupViewLiveDataObservers() {
//        viewModel.users.observe(viewLifecycleOwner) { source ->
//            when (source) {
////                is Resource.Success -> source.dataEvent?.let { adapter.submitList(it) }
////                is Resource.Error -> {
//////                    TODO: error handling
////                }
////                else -> Unit
//            }
//        }
    }

    override fun onDestroyView() {
        rvFirstUsers.adapter = null
        super.onDestroyView()
    }
}