package com.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EmployeeEntity(
    @PrimaryKey val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val gender: String,
)