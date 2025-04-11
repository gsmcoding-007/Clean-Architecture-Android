package com.gsmcoding.cleanarchitectureandroidapp.domain.usecase

import com.gsmcoding.cleanarchitectureandroidapp.data.local.entity.UserEntity
import com.gsmcoding.cleanarchitectureandroidapp.domain.model.User
import com.gsmcoding.cleanarchitectureandroidapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UserRepository
) {
//    suspend operator fun invoke(): Flow<List<UserEntity>> = repository.getLocalUsers()
}