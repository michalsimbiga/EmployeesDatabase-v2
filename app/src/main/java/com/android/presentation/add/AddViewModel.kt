package com.android.presentation.add

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.domain.usecases.AddEmployeeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AddViewModel @Inject constructor(
    private val addEmployeeUseCase: AddEmployeeUseCase
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
            onSuccess = {
                Log.d("VUKO", "Added to db")
                _navigation.value = true
            },
            onFailure = {
                Log.d("VUKO", "Not Added to db $it")
                _navigation.value = true
            }
        )
    }


}

