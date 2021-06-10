package com.android.presentation.edit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.model.AddressItem
import com.android.model.EmployeeItem
import com.android.model.toDomain
import com.domain.models.Employee
import com.domain.usecases.AddAddressUseCase
import com.domain.usecases.AddEmployeeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class EditViewModel @Inject constructor(
    private val addEmployeeUseCase: AddEmployeeUseCase,
    private val addAddressUseCase: AddAddressUseCase
) : ViewModel() {

    val firstName = MutableLiveData("")
    val lastName = MutableLiveData("")
    val age = MutableLiveData("")
    private var gender = ""

    private val _addressess =
        MutableStateFlow<List<AddressViewType>>(listOf(AddressViewType.AddNew))
    val addressess: StateFlow<List<AddressViewType>> = _addressess

    private val _navigation: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val navigation: StateFlow<Boolean> = _navigation
    
    private var savedEmployee : EmployeeItem? = null

    fun addEmployeeToDatabase() {
        val employee = Employee(
            firstName = firstName.value.toString(),
            lastName = lastName.value.toString(),
            age = age.value.toString().toInt(),
            gender = gender
        )

        addEmployeeUseCase(
            viewModelScope,
            Dispatchers.IO,
            employee,
            onSuccess = { employeeId -> addAddressesToDb(employeeId) },
        )
    }

    fun setEditMode(employee: EmployeeItem) {
        savedEmployee = employee
        firstName.value = employee.firstName
        lastName.value = employee.lastName
        age.value = employee.age.toString()
        gender = employee.gender

        val addressesTypes = employee.addressess.map { AddressViewType.Filled(it) }
        _addressess.value = addressesTypes + _addressess.value
    }

    fun updateGender(newGender: String) {
        gender = newGender
    }

    private fun addAddressesToDb(employeeId: Long) {
        val addresses = _addressess.value.filterIsInstance<AddressViewType.Filled>().map {
            it.address.copy(employeeId = employeeId).toDomain()
        }

        addAddressUseCase(
            viewModelScope,
            dispatcher = Dispatchers.IO,
            addresses,
            onSuccess = { _navigation.value = true }
        )
    }

    fun onAddNewAddressClicked() {
        val newAddresses = _addressess.value.toMutableList().apply {
            removeAll { it is AddressViewType.AddNew }
            add(AddressViewType.Editable(AddressItem()))
        }
        _addressess.value = newAddresses
    }

    fun onDiscardAddressClicked() {
        val newAddresses = _addressess.value.toMutableList().apply {
            removeAll { it is AddressViewType.Editable }
            add(AddressViewType.AddNew)
        }
        _addressess.value = newAddresses
    }

    fun onConfirmAddressClicked(addressItem: AddressItem) {
        val newAddresses = _addressess.value.toMutableList().apply {
            removeAll { it is AddressViewType.Editable }
            add(AddressViewType.Filled(addressItem))
            add(AddressViewType.AddNew)
        }
        _addressess.value = newAddresses
    }


}

