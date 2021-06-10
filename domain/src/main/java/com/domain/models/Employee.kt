package com.domain.models

data class Employee(
    val id: Int? = null,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val gender: String,
    val addresses: List<Address> = listOf()
)