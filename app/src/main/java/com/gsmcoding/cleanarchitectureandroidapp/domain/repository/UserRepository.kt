package com.gsmcoding.cleanarchitectureandroidapp.domain.repository

import com.gsmcoding.cleanarchitectureandroidapp.data.local.entity.UserEntity
import com.gsmcoding.cleanarchitectureandroidapp.domain.model.User
import kotlinx.coroutines.flow.Flow

//interface UserRepository {
//    suspend fun getUsers(): List<User>
//}

interface UserRepository {
    fun getLocalUsers(): Flow<List<User>>
    fun getRemoteUsers(): Flow<List<User>>
    suspend fun saveUsersLocally(users: List<User>)
}
