package com.domain.repositories

import com.domain.models.Address
import com.domain.models.Employee
import kotlinx.coroutines.flow.Flow

interface EmployeesRepository {

    suspend fun getAllEmployees(): Flow<List<Employee>>
    suspend fun insertEmployee(employee: Employee): Long
    suspend fun updateEmployee(employee: Employee): Unit
    suspend fun deleteEmployee(employee: Employee): Unit
    suspend fun deleteAddress(address: Address): Unit
    suspend fun insertAddresses(addresses: List<Address>): Unit
}
