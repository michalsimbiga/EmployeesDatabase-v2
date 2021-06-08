package com.data.mappers

import com.data.networking.models.UserNameApiModel
import com.domain.models.UserName

fun UserNameApiModel.toDomain() = UserName(
    title as String,
    first as String,
    last as String
)