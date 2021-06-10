package com.data.database.models

import androidx.room.Embedded
import androidx.room.Relation

data class EmployeeWithAddressesEntity(
    @Embedded val employee: EmployeeEntity,

    @Relation(
        parentColumn = "employeeId",
        entityColumn = "employeeId"
    )
    val addresses: List<AddressEntity>
)
