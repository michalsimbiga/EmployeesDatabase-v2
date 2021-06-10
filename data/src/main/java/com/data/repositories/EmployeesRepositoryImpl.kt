package com.data.repositories

import com.data.database.ApplicationDatabase
import com.data.mappers.toDomain
import com.data.mappers.toEntity
import com.domain.models.Address
import com.domain.models.Employee
import com.domain.repositories.EmployeesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class EmployeesRepositoryImpl @Inject constructor(private val database: ApplicationDatabase) :
    EmployeesRepository {

    override suspend fun getAllEmployees(): Flow<List<Employee>> =
        database.employeesDao().getAllEmployees()
            .map { it.map { employeeWithAddressesEntity -> employeeWithAddressesEntity.toDomain() } }

    override suspend fun insertEmployee(employee: Employee): Unit =
        database.employeesDao().insertEmployee(employee.toEntity())

    override suspend fun updateEmployee(employee: Employee): Unit =
        database.employeesDao().updateEmployee(employee = employee.toEntity())

    override suspend fun deleteEmployee(employee: Employee): Unit =
        database.employeesDao().deleteEmployee(employee = employee.toEntity())

    override suspend fun deleteAddress(address: Address): Unit =
        database.addressDao().deleteAddress(address = address.toEntity())
}