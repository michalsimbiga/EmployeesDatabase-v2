package com.data.database.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = EmployeeEntity::class,
        parentColumns = ["employeeId"],
        childColumns = ["employeeId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class AddressEntity(
    @PrimaryKey(autoGenerate = true) val addressId: Int?,
    val street: String,
    val city: String,
    val zip: String,
    val country: String,
    val employeeId: Long,
)