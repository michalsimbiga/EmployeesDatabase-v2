package com.android.presentation.add

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.model.AddressItem
import com.android.model.toDomain
import com.domain.usecases.AddAddressUseCase
import com.domain.usecases.AddEmployeeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AddViewModel @Inject constructor(
    private val addEmployeeUseCase: AddEmployeeUseCase,
    private val addAddressUseCase: AddAddressUseCase
) : ViewModel() {

    private val _addressess =
        MutableStateFlow<List<AddressViewType>>(listOf(AddressViewType.AddNew))
    val addressess: StateFlow<List<AddressViewType>> = _addressess

    private val _navigation: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val navigation: StateFlow<Boolean> = _navigation

    fun addEmployeeToDatabase(firstName: String, lastName: String, age: Int, gender: String) {
        addEmployeeUseCase(
            viewModelScope,
            Dispatchers.IO,
            AddEmployeeUseCase.AddEmployeeParams(
                firstName,
                lastName,
                age,
                gender
            ),
            onSuccess = { employeeId ->
                addAddressesToDb(employeeId)
            },
            onFailure = {
                Log.d("VUKO", "Not Added to db $it")
                _navigation.value = false
            }
        )
    }

    private fun addAddressesToDb(employeeId: Long) {
        val addresses = _addressess.value.filterIsInstance<AddressViewType.Filled>()

        addresses.forEach { address ->
            addAddressUseCase(
                viewModelScope,
                dispatcher = Dispatchers.IO,
                address.address
                    .copy(employeeId = employeeId)
                    .toDomain(),
                onSuccess = { if (address == addresses.last()) _navigation.value = true }
            )
        }
    }

    fun onAddNewAddressClicked() {
        val currentValue = _addressess.value.toMutableList().apply {
            removeAll { it is AddressViewType.AddNew }
            add(AddressViewType.Editable(AddressItem()))
        }
        _addressess.value = currentValue
    }

    fun onDiscardAddressClicked() {
        val currentValue = _addressess.value.toMutableList().apply {
            removeAll { it is AddressViewType.Editable }
            add(AddressViewType.AddNew)
        }
        _addressess.value = currentValue
    }

    fun onConfirmAddressClicked(addressItem: AddressItem) {
        val currentValue = _addressess.value.toMutableList().apply {
            removeAll { it is AddressViewType.Editable }
            add(AddressViewType.Filled(addressItem))
            add(AddressViewType.AddNew)
        }
        _addressess.value = currentValue
    }


}

