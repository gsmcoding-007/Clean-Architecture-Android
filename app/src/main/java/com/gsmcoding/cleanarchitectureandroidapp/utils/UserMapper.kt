package com.gsmcoding.cleanarchitectureandroidapp.utils

import com.gsmcoding.cleanarchitectureandroidapp.data.local.entity.UserEntity
import com.gsmcoding.cleanarchitectureandroidapp.domain.model.User

fun UserEntity.toDomain(): User {
    return User(
        uid = uid,
        name = name,
        email = email,
        profileUrl = profileUrl
    )
}

fun List<UserEntity>.toDomainList(): List<User> = map { it.toDomain() }

fun User.toEntity(): UserEntity {
    return UserEntity(
        uid = uid,
        name = name,
        email = email,
        profileUrl = profileUrl
    )
}

fun List<User>.toEntityList(): List<UserEntity> = map { it.toEntity() }