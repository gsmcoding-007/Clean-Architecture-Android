package com.gsmcoding.cleanarchitectureandroidapp.data.model

import com.gsmcoding.cleanarchitectureandroidapp.domain.model.User

data class UserDto(
    val id: Int,
    val name: String,
    val email: String
) {
    fun toDomain(): User = User(id.toString(), name, email)
}