package com.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AddressEntity(
    @PrimaryKey val id: Int,
    val street: String,
    val city: String,
    val zip: String,
    val country: String
)