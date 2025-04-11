package com.gsmcoding.cleanarchitectureandroidapp.presentation.user.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gsmcoding.cleanarchitectureandroidapp.domain.repository.UserRepository
import com.gsmcoding.cleanarchitectureandroidapp.presentation.user.ui.UserUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
//class UserViewModel @Inject constructor(
//    private val getUsersUseCase: GetUsersUseCase
//) : ViewModel() {
//
//    private val _users = MutableStateFlow<List<User>>(emptyList())
//    val users: StateFlow<List<User>> = _users.asStateFlow()
//
//    init {
//        viewModelScope.launch {
//            _users.value = getUsersUseCase()
//        }
//    }
//}


@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

//    private val _users = MutableStateFlow<List<User>>(emptyList())
//    val users: StateFlow<List<User>> = _users.asStateFlow()
//
//    init {
//        viewModelScope.launch {
//            repository.getRemoteUsers().collect { remoteUsers ->
//                repository.saveUsersLocally(remoteUsers)
//            }
//        }
//
//        viewModelScope.launch {
//            repository.getLocalUsers().collect {
//                _users.value = it
//            }
//        }
//    }

    private val _uiState = MutableStateFlow<UserUiState>(UserUiState.Loading)
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                repository.getRemoteUsers().collect { remoteUsers ->
                    repository.saveUsersLocally(remoteUsers)
                }
            } catch (e: Exception) {
                _uiState.value = UserUiState.Error("Failed to load remote users")
            }
        }

        viewModelScope.launch {
            repository.getLocalUsers().collect {
                _uiState.value = UserUiState.Success(it)
            }
        }
    }

}
