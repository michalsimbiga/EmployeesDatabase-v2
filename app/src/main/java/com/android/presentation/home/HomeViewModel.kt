package com.android.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.model.EmployeeItem
import com.android.model.toDomain
import com.android.model.toItem
import com.domain.models.Employee
import com.domain.usecases.DeleteEmployeeUseCase
import com.domain.usecases.GetEmployeesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


class HomeViewModel @Inject constructor(
    private val getAllEmployeesUseCase: GetEmployeesUseCase,
    private val deleteEmployeeUseCase: DeleteEmployeeUseCase
) : ViewModel() {

    init {
        getAllEmployees()
    }

    private val _uiState: MutableStateFlow<List<EmployeeItem>> = MutableStateFlow(emptyList())
    val uiState: StateFlow<List<EmployeeItem>> = _uiState

    private val _employees = MutableLiveData<List<EmployeeItem>>()
    val employees: LiveData<List<EmployeeItem>> = _employees

    fun deleteEmployee(employee: EmployeeItem) {
        deleteEmployeeUseCase(
            viewModelScope,
            Dispatchers.Default,
            employee.toDomain(),
            onSuccess = { getAllEmployees() },
            onFailure = {}
        )
    }

    private fun getAllEmployees() =
        getAllEmployeesUseCase(
            viewModelScope,
            Dispatchers.IO,
            Unit,
            onSuccess = ::onSuccess,
            onFailure = {
                Log.d("VUKO", "Not Got from db $it")
            }
        )

    private fun onSuccess(flow: Flow<List<Employee>>) {
        Log.d("VUKO", "Got from db $flow")
        viewModelScope.launch {
            flow.collect {
                Log.d("VUKO", "Got from db collect $it")
                _uiState.value = it.map { it.toItem() }
                _employees.value = it.map { it.toItem() }
            }
        }
    }


}
