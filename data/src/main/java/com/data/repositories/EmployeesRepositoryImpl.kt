package com.data.repositories

import com.data.database.ApplicationDatabase
import com.data.mappers.toDomain
import com.data.mappers.toEntity
import com.domain.models.Address
import com.domain.models.Employee
import com.domain.repositories.EmployeesRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class EmployeesRepositoryImpl @Inject constructor(private val database: ApplicationDatabase) :
    EmployeesRepository {

    override suspend fun getAllEmployees() =
        database.employeesDao().getAllEmployees()
            .map { it.map { employeeWithAddressesEntity -> employeeWithAddressesEntity.toDomain() } }

    override suspend fun insertEmployee(employee: Employee) =
        database.employeesDao().insertEmployee(employee.toEntity())

    override suspend fun updateEmployee(employee: Employee) =
        database.employeesDao().updateEmployee(employee = employee.toEntity())

    override suspend fun deleteEmployee(employee: Employee) =
        database.employeesDao().deleteEmployee(employee = employee.toEntity())

    override suspend fun deleteAddress(address: Address) =
        database.addressDao().deleteAddress(address = address.toEntity())

    override suspend fun insertAddresses(addresses: List<Address>) =
        database.addressDao()
            .insertAddresses(addresses = addresses.map { it.toEntity() }.toTypedArray())
}