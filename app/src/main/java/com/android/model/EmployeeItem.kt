package com.android.model

import android.os.Parcelable
import com.core.extensions.empty
import com.domain.models.Employee
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EmployeeItem(
    val id: Int? = null,
    val firstName: String = String.empty,
    val lastName: String = String.empty,
    val age: Int = -1,
    val gender: String = String.empty,
    val addressess: List<AddressItem> = listOf()
) : Parcelable

fun Employee.toItem() =
    EmployeeItem(
        id = id,
        firstName = firstName,
        lastName = lastName,
        age = age,
        gender = gender,
        addressess = addresses.map { it.toItem() }
    )

fun EmployeeItem.toDomain() =
    Employee(
        id = id,
        firstName = firstName,
        lastName = lastName,
        age = age,
        gender = gender,
        addresses = emptyList()
    )