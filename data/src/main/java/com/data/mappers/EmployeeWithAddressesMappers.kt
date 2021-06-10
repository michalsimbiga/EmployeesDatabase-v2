package com.data.mappers

import com.data.database.models.EmployeeWithAddressesEntity
import com.domain.models.Employee

fun EmployeeWithAddressesEntity.toDomain() =
    Employee(
        id = this.employee.employeeId,
        firstName = this.employee.firstName,
        lastName = this.employee.lastName,
        age = this.employee.age,
        gender = this.employee.gender,
        addresses = this.addresses.map { address -> address.toDomain() }
    )