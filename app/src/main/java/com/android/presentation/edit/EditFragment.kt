package com.android.presentation.edit

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.android.di.add.AddInjector
import com.core.ui.BaseFragment
import com.employeedatabase.R
import com.employeedatabase.databinding.FragmentEditBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditFragment(override val layoutId: Int = R.layout.fragment_edit) :
    BaseFragment<FragmentEditBinding>() {

    private val injector: AddInjector
        get() = baseActivity.application as AddInjector

    @Inject
    lateinit var navigator: AddNavigator

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    private val viewModel: EditViewModel by viewModels { viewModelProviderFactory }

    private val adapter by lazy {
        AddressesAdapter(
            onAddNewAddressClick = { viewModel.onAddNewAddressClicked() },
            onConfirmAddressClick = { address -> viewModel.onConfirmAddressClicked(address) },
            onRemoveAddressClick = { viewModel.onDiscardAddressClicked() },
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
        binding.rvEditAddresses.adapter = adapter
    }

    private fun setupListeners() = with(binding) {

        addEmployeeButton.setOnClickListener {
            val selectedRadioButtonId =
                editableEmployeeGenderRadioGroup.checkedRadioButtonId
            val selectedRadioText: String =
                root.findViewById<RadioButton>(selectedRadioButtonId)?.text?.toString() ?: ""

            viewModel.addEmployeeToDatabase(
                editableEmployeeFirstNameTextEdit.text.toString(),
                editableEmployeeLastNameTextEdit.text.toString(),
                editableEmployeeAgeTextEdit.text.toString().toInt(),
                selectedRadioText
            )
        }
    }

    private fun setupObservers() {

        lifecycleScope.launch {
            viewModel.addressess.collect { addressViewType ->
                adapter.submitList(addressViewType)
            }
        }

        lifecycleScope.launch {
            viewModel.navigation.collect { navigate ->
                if (navigate) navigator.navigateToHome()
            }
        }
    }

    override fun onDestroyView() {
        binding.rvEditAddresses.adapter = null
        super.onDestroyView()
    }
}