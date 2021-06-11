package com.android.presentation.edit

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
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

    @Inject
    lateinit var navigator: EditNavigator

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    private val injector: EditInjector
        get() = baseActivity.application as EditInjector

    private val args by navArgs<EditFragmentArgs>()

    private val fragmentViewModel: EditViewModel by viewModels { viewModelProviderFactory }

    private val adapter by lazy {
        AddressesAdapter(
            onAddNewAddressClick = { fragmentViewModel.onAddNewAddressClicked() },
            onConfirmAddressClick = { address -> fragmentViewModel.onConfirmAddressClicked(address) },
            onRemoveAddressClick = { fragmentViewModel.onDiscardAddressClicked() }
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

        radioEditGender.setOnCheckedChangeListener { _, checkedId ->
            root.findViewById<RadioButton>(checkedId)?.let {
                fragmentViewModel.updateGender(it.text.toString())
            }
        }

        buttonEditDone.setOnClickListener {
            if (validateData()) fragmentViewModel.addEmployeeToDatabase()
            else Toast.makeText(context, "Invalid data", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateData(): Boolean = with(binding) {
        return textEditAge.text.toString().isNotEmpty() &&
                textEditFirstName.text.toString().isNotEmpty() &&
                textEditLastName.text.toString().isNotEmpty()
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