package com.domain.usecases

import com.domain.models.Employee
import com.domain.repositories.EmployeesRepository
import com.domain.usecases.base.UseCase
import javax.inject.Inject

class DeleteEmployeeUseCase @Inject constructor(
    private val repository: EmployeesRepository
) : UseCase<Employee, Unit>() {

    override suspend fun run(params: Employee): Unit {
        return repository.deleteEmployee(params)
    }

}