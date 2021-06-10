package com.data.mappers

import com.data.database.models.EmployeeEntity
import com.domain.models.Employee

fun EmployeeEntity.toDomain() =
    Employee(
        id = employeeId,
        firstName = firstName,
        lastName = lastName,
        age = age,
        gender = gender,
        addresses = listOf()
    )

fun Employee.toEntity() =
    EmployeeEntity(
        employeeId = id,
        firstName = firstName,
        lastName = lastName,
        age = age,
        gender = gender,
    )