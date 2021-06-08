package com.domain.usecases

import com.domain.models.Employee
import com.domain.repositories.EmployeesRepository
import com.domain.usecases.base.UseCase
import javax.inject.Inject

class AddEmployeeUseCase @Inject constructor(
    private val repository: EmployeesRepository
) : UseCase<AddEmployeeUseCase.AddEmployeeParams, Unit>() {

    override suspend fun run(params: AddEmployeeParams): Unit {
        val employee = Employee(
            id = null,
            firstName = params.name,
            lastName = params.lastName,
            age = params.age,
            gender = params.gender,
            addressess = emptyList()
        )
        return repository.insertEmployee(employee)
    }

    data class AddEmployeeParams(
        val name: String,
        val lastName: String,
        val age: Int,
        val gender: String,
    )
}