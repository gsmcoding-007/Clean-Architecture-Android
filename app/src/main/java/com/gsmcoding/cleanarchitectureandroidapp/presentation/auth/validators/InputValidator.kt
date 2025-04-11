package com.gsmcoding.cleanarchitectureandroidapp.presentation.auth.validators

object InputValidator {
    fun isEmailValid(email: String): Boolean =
        android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun isPasswordValid(password: String): Boolean = password.length >= 6

    fun isNameValid(name: String): Boolean = name.isNotBlank()
}