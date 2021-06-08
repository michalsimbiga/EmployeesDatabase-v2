package com.android.model

import android.os.Parcelable
import com.core.extensions.empty
import com.domain.models.Employee
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EmployeeItem(
    val id: Int ,
    val firstName: String = String.empty,
    val lastName: String = String.empty,
    val age: Int = -1,
    val gender: String = String.empty,
    val addressess: List<AddressItem> = listOf()
) : Parcelable {
    fun isEmpty() =
        firstName.trim() == String.empty &&
                lastName.trim() == String.empty &&
                age == -1 &&
                gender.trim() == String.empty
}

fun Employee.toItem() =
    EmployeeItem(
        id = id,
        firstName = firstName,
        lastName = lastName,
        age = age,
        gender = gender,
        addressess = emptyList()
    )

fun EmployeeItem.toDomain() =
    Employee(
        id = id,
        firstName = firstName,
        lastName = lastName,
        age = age,
        gender = gender,
        addressess =  emptyList()
    )
