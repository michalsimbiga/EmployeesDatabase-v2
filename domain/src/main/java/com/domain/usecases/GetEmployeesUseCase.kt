package com.domain.usecases

import com.domain.models.Employee
import com.domain.repositories.EmployeesRepository
import com.domain.usecases.base.UseCase
import javax.inject.Inject

class GetEmployeesUseCase @Inject constructor(
    private val repository: EmployeesRepository
) : UseCase<Unit, List<Employee>>() {

    override suspend fun run(params: Unit): List<Employee> = repository.getAllEmployees()
}