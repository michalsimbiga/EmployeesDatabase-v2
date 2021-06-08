package com.data.mappers

import com.data.database.models.EmployeeEntity
import com.domain.models.Employee

fun EmployeeEntity.toDomain() =
    Employee(
        id = id,
        firstName = firstName,
        lastName = lastName,
        age = age,
        gender = gender,
        addressess = listOf()
    )

fun Employee.toEntity() =
    EmployeeEntity(
        id = id,
        firstName = firstName,
        lastName = lastName,
        age = age,
        gender = gender,
    )