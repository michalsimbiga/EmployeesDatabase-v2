package com.data.mappers

import com.data.networking.models.UserIdApiModel
import com.domain.models.UserId

fun UserIdApiModel.toDomain() = UserId(
    name as String,
    value
)