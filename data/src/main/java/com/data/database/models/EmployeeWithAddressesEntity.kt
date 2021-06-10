package com.data.database.models

import androidx.room.Embedded
import androidx.room.Relation

data class EmployeeWithAddressesEntity(
    @Embedded val employee: EmployeeEntity,

    @Relation(
        parentColumn = "employeeId",
        entityColumn = "addressId"
    )
    val addresses: List<AddressEntity>
)
