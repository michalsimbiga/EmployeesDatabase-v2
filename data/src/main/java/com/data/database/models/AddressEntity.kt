package com.data.database.models

import androidx.room.Entity

@Entity
data class AddressEntity(
    val id: Long? = null,
    val street: String,
    val city: String,
    val zip: String,
    val country: String
)