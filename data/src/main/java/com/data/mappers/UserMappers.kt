package com.data.mappers

import com.data.networking.models.UserApiModel
import com.domain.models.User
import com.domain.models.UserId
import com.domain.models.UserName
import com.domain.models.UserPicture

fun UserApiModel.toDomain() = User(
    id?.toDomain() as UserId,
    name?.toDomain() as UserName,
    picture?.toDomain() as UserPicture,
    gender as String
)