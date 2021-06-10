package com.data.mappers

import com.data.database.models.AddressEntity
import com.domain.models.Address

fun AddressEntity.toDomain() =
    Address(
        id = addressId,
        street = street,
        city = city,
        zip = zip,
        country = country,
        employeeId = employeeId
    )

fun Address.toEntity() =
    AddressEntity(
        addressId = id,
        street = street,
        city = city,
        zip = zip,
        country = country,
        employeeId = employeeId
    )