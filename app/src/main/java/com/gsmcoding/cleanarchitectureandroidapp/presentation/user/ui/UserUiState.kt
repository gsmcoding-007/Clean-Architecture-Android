package com.gsmcoding.cleanarchitectureandroidapp.presentation.user.ui

import com.gsmcoding.cleanarchitectureandroidapp.domain.model.User

sealed class UserUiState {
    object Loading : UserUiState()
    data class Success(val users: List<User>) : UserUiState()
    data class Error(val message: String) : UserUiState()
}
