package com.gsmcoding.cleanarchitectureandroidapp.presentation.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.gsmcoding.cleanarchitectureandroidapp.domain.model.User
import com.gsmcoding.cleanarchitectureandroidapp.presentation.auth.ui.AuthUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : ViewModel() {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState: StateFlow<AuthUiState> = _uiState

    fun login(email: String, password: String) = viewModelScope.launch {
        _uiState.value = AuthUiState.Loading
        try {
            auth.signInWithEmailAndPassword(email, password).await()
            _uiState.value = AuthUiState.Success
        } catch (e: Exception) {
            _uiState.value = AuthUiState.Error(e.message ?: "Login failed")
        }
    }

    fun signup(name: String, email: String, password: String) = viewModelScope.launch {
        _uiState.value = AuthUiState.Loading
        try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            val userId = result.user?.uid ?: return@launch
            val user = User(uid = userId, name = name, email = email)
            firestore.collection("users").document(userId).set(user).await()
            _uiState.value = AuthUiState.Success
        } catch (e: Exception) {
            _uiState.value = AuthUiState.Error(e.message ?: "Signup failed")
        }
    }
}

