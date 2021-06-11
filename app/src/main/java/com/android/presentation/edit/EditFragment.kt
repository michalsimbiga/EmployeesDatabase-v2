package com.android.presentation.edit

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.android.di.edit.EditInjector
import com.core.ui.BaseFragment
import com.employeedatabase.R
import com.employeedatabase.databinding.FragmentEditBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditFragment(override val layoutId: Int = R.layout.fragment_edit) :
    BaseFragment<FragmentEditBinding>() {

    private val injector: EditInjector
        get() = baseActivity.application as EditInjector

    @Inject
    lateinit var navigator: EditNavigator

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    private val args by navArgs<EditFragmentArgs>()

    private val fragmentViewModel: EditViewModel by viewModels { viewModelProviderFactory }

    private val adapter by lazy {
        AddressesAdapter(
            onAddNewAddressClick = { fragmentViewModel.onAddNewAddressClicked() },
            onConfirmAddressClick = { address -> fragmentViewModel.onConfirmAddressClicked(address) },
            onRemoveAddressClick = { fragmentViewModel.onDiscardAddressClicked() },
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
        args.employee?.let { fragmentViewModel.setEditMode(it) }

        with(binding) {
            rvEditAddresses.adapter = adapter
            viewModel = fragmentViewModel
        }
    }

    private fun setupListeners() = with(binding) {

        editableEmployeeGenderRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            val selectedRadioText: String =
                root.findViewById<RadioButton>(checkedId)?.text?.toString() ?: ""

            fragmentViewModel.updateGender(selectedRadioText)
        }

        addEmployeeButton.setOnClickListener {
            fragmentViewModel.addEmployeeToDatabase()
        }
    }

    private fun setupObservers() {

        lifecycleScope.launch {
            fragmentViewModel.addressess.collect { addressViewType ->
                adapter.submitList(addressViewType)
            }
        }

        lifecycleScope.launch {
            fragmentViewModel.navigation.collect { navigate ->
                if (navigate) navigator.navigateToHome()
            }
        }
    }

    override fun onDestroyView() {
        binding.rvEditAddresses.adapter = null
        super.onDestroyView()
    }
}