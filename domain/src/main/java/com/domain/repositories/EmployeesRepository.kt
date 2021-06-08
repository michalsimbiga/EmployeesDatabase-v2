package com.domain.repositories

import com.domain.models.Address
import com.domain.models.Employee

interface EmployeesRepository {

    suspend fun getAllEmployees(): List<Employee>
    suspend fun getEmployeeById(employeeId: Long): Employee
    suspend fun insertEmployee(employee: Employee): Unit
    suspend fun updateEmployee(employee: Employee): Unit
    suspend fun deleteEmployee(employee: Employee): Unit
    suspend fun deleteAddress(address: Address): Unit
}
