package com.android.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.model.EmployeeItem
import com.android.model.toDomain
import com.android.model.toItem
import com.domain.models.Employee
import com.domain.usecases.DeleteEmployeeUseCase
import com.domain.usecases.GetEmployeesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getAllEmployeesUseCase: GetEmployeesUseCase,
    private val deleteEmployeeUseCase: DeleteEmployeeUseCase
) : ViewModel() {

    init {
        getAllEmployees()
    }

    private val _employees: MutableStateFlow<List<EmployeeItem>> = MutableStateFlow(emptyList())
    val employees: StateFlow<List<EmployeeItem>> = _employees

    fun deleteEmployee(employee: EmployeeItem) {
        deleteEmployeeUseCase(
            viewModelScope,
            Dispatchers.Default,
            employee.toDomain(),
            onSuccess = { getAllEmployees() }
        )
    }

    private fun getAllEmployees() =
        getAllEmployeesUseCase(
            viewModelScope,
            Dispatchers.IO,
            Unit,
            onSuccess = ::onSuccess
        )

    private fun onSuccess(flow: Flow<List<Employee>>) {
        viewModelScope.launch {
            flow.collect {
                _employees.value = it.map { employee -> employee.toItem() }
            }
        }
    }
}
