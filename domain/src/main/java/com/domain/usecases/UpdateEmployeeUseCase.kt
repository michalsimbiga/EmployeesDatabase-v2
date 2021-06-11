package com.domain.usecases

import com.domain.models.Employee
import com.domain.repositories.EmployeesRepository
import com.domain.usecases.base.UseCase
import javax.inject.Inject

class UpdateEmployeeUseCase @Inject constructor(
    private val repository: EmployeesRepository
) : UseCase<Employee, Unit>() {

    override suspend fun run(params: Employee) = repository.updateEmployee(params)
}