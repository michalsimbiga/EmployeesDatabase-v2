package com.data.networking.models

data class UserApiModel(
    val id: UserIdApiModel?,
    val gender: String?,
    val picture: UserPictureApiModel?,
    val name: UserNameApiModel?
)