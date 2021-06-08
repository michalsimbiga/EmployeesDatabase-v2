package com.data.mappers

import com.data.database.models.AddressEntity
import com.domain.models.Address

fun AddressEntity.toDomain() =
    Address(
        id = id ?: -1,
        street = street,
        city = city,
        zip = zip,
        country = country
    )

fun Address.toEntity() =
    AddressEntity(
        id = id,
        street = street,
        city = city,
        zip = zip,
        country = country
    )