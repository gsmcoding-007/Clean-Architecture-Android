package com.gsmcoding.cleanarchitectureandroidapp.data.repository

import com.gsmcoding.cleanarchitectureandroidapp.data.api.ApiService
import com.gsmcoding.cleanarchitectureandroidapp.data.local.dao.UserDao
import com.gsmcoding.cleanarchitectureandroidapp.data.local.entity.UserEntity
import com.gsmcoding.cleanarchitectureandroidapp.data.remote.FirebaseUserService
import com.gsmcoding.cleanarchitectureandroidapp.domain.model.User
import com.gsmcoding.cleanarchitectureandroidapp.domain.repository.UserRepository
import com.gsmcoding.cleanarchitectureandroidapp.utils.toDomainList
import com.gsmcoding.cleanarchitectureandroidapp.utils.toEntityList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

//class UserRepositoryImpl @Inject constructor(
//    private val api: ApiService,
//    private val userDao: UserDao
//) : UserRepository {
//    override suspend fun getUsers(): List<User> {
//        return try {
//            val users = api.getUsers().map { it.toDomain() }
//            userDao.insertUsers(users.map { UserEntity.fromDomain(it) })
//            users
//        } catch (e: Exception) {
//            userDao.getUsers().map { it.toDomain() }
//        }
//    }
//}

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val firebaseService: FirebaseUserService
) : UserRepository {

    override fun getLocalUsers(): Flow<List<User>> = userDao.getAllUsers().map { it.toDomainList() }

    override fun getRemoteUsers(): Flow<List<User>> = firebaseService.observeUsers()

    override suspend fun saveUsersLocally(users: List<User>) {
        userDao.clearUsers()
        userDao.insertAll(users.toEntityList())
    }
}
