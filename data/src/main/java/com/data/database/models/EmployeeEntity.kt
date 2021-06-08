package com.data.database.models

import androidx.room.Entity

@Entity
data class EmployeeEntity(
    val id: Long? = null,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val gender: String,
    val addressess: List<AddressEntity>
)