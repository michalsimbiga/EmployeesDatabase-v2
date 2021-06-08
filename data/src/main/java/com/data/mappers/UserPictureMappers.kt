package com.data.mappers

import com.data.networking.models.UserPictureApiModel
import com.domain.models.UserPicture

fun UserPictureApiModel.toDomain() = UserPicture(
    large as String,
    medium as String,
    thumbnail as String
)