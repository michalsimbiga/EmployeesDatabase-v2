package com.domain.usecases

import com.domain.models.Employee
import com.domain.repositories.EmployeesRepository
import com.domain.usecases.base.UseCase
import javax.inject.Inject

class AddEmployeeUseCase @Inject constructor(
    private val repository: EmployeesRepository
) : UseCase<Employee, Long>() {

    override suspend fun run(params: Employee): Long {
        return repository.insertEmployee(params)
    }

    data class AddEmployeeParams(
        val name: String,
        val lastName: String,
        val age: Int,
        val gender: String,
    )
}