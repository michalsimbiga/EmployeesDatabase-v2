package com.data.mappers

import com.data.database.models.EmployeeEntity
import com.domain.models.Employee

fun EmployeeEntity.toDomain() =
    Employee(
        id = id ?: -1,
        firstName = firstName,
        lastName = lastName,
        age = age,
        gender = gender,
        addressess = addressess.map { it.toDomain() }
    )

fun Employee.toEntity() =
    EmployeeEntity(
        id = id,
        firstName = firstName,
        lastName = lastName,
        age = age,
        gender = gender,
        addressess = addressess.map { it.toEntity() }
    )