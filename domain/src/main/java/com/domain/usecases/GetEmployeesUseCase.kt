package com.domain.usecases

import com.domain.models.Employee
import com.domain.repositories.EmployeesRepository
import com.domain.usecases.base.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetEmployeesUseCase @Inject constructor(
    private val repository: EmployeesRepository
) : UseCase<Unit, Flow<List<Employee>>>() {

    override suspend fun run(params: Unit): Flow<List<Employee>> =
        repository.getAllEmployees()
}