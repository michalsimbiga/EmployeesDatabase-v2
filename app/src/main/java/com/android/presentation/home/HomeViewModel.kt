package com.android.presentation.home

import androidx.lifecycle.ViewModel
import com.domain.usecases.GetEmployeesUseCase
import javax.inject.Inject


class HomeViewModel @Inject constructor(
    private val getAllEmployeesUseCase: GetEmployeesUseCase
) : ViewModel() {

//
//    fun getAllEmployees() = viewModelScope.launch(Dispatchers.Default) {
//        delay(2000)
//
//        getAllEmployeesUseCase.execute(
//            mapper = { listOfEmployees -> listOfEmployees.map(Employee::toItem) },
//            stateReducer = { copy(listOfEmployees = it) }
//        )
//    }
//
//    fun deleteEmployee(employee: EmployeeItem) = viewModelScope.launch(Dispatchers.Default) {
//        deleteEmployeeUseCase.invoke(
//            params = DeleteEmployeeUseCase.Params(employee.toDomain()),
//            onSuccess = { getAllEmployees() },
//            onFailure = { setState { copy(listOfEmployees = Fail(it)) } }
//        )
//    }

}
