package com.data.networking.models.wrappers

import com.data.networking.models.UserApiModel

data class UsersResponse(
    val results: List<UserApiModel>
)