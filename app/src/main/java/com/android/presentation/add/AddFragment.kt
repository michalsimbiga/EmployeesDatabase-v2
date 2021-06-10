package com.android.presentation.add

import android.content.Context
import android.os.Bundle
import android.util.Log
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

class AddFragment(override val layoutId: Int = R.layout.fragment_edit) :
    BaseFragment<FragmentEditBinding>() {

    private val injector: AddInjector
        get() = baseActivity.application as AddInjector

    @Inject
    lateinit var navigator: AddNavigator

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    private val viewModel: AddViewModel by viewModels { viewModelProviderFactory }

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

        binding.addEmployeeButton.setOnClickListener {
            val selectedRadioButtonId =
                binding.editableEmployeeGenderRadioGroup.checkedRadioButtonId
            val selectedRadioText: String =
                binding.root.findViewById<RadioButton>(selectedRadioButtonId)?.text?.toString()
                    ?: ""

            viewModel.addEmployeeToDatabase(
                binding.editableEmployeeFirstNameTextEdit.text.toString(),
                binding.editableEmployeeLastNameTextEdit.text.toString(),
                binding.editableEmployeeAgeTextEdit.text.toString().toInt(),
                selectedRadioText
            )
        }

        binding.rvEditAddresses.adapter = adapter

        lifecycleScope.launch {
            viewModel.addressess.collect { addressViewType ->
                Log.d("VUKO", "Addressess $addressViewType")
                adapter.submitList(addressViewType)
            }
        }

        lifecycleScope.launch {
            viewModel.navigation.collect { navigate ->
                if (navigate) navigator.navigateToHome()
            }
        }

    }

}