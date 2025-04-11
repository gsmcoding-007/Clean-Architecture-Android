package com.gsmcoding.cleanarchitectureandroidapp.presentation.auth.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.gsmcoding.cleanarchitectureandroidapp.databinding.ActivitySignupBinding
import com.gsmcoding.cleanarchitectureandroidapp.presentation.auth.validators.InputValidator
import com.gsmcoding.cleanarchitectureandroidapp.presentation.auth.viewmodel.LoginViewModel
import com.gsmcoding.cleanarchitectureandroidapp.presentation.user.ui.UserActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignup.setOnClickListener {
            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (!InputValidator.isNameValid(name)) {
                binding.layoutName.error = "Name cannot be empty"
                return@setOnClickListener
            } else binding.layoutName.error = null

            if (!InputValidator.isEmailValid(email)) {
                binding.layoutEmail.error = "Invalid email"
                return@setOnClickListener
            } else binding.layoutEmail.error = null

            if (!InputValidator.isPasswordValid(password)) {
                binding.layoutPassword.error = "Password must be 6+ characters"
                return@setOnClickListener
            } else binding.layoutPassword.error = null

            viewModel.signup(name, email, password)
        }

        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { state ->
                when (state) {
                    is AuthUiState.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is AuthUiState.Success -> navigateToHome()
                    is AuthUiState.Error -> showError(state.message)
                    else -> Unit
                }
            }
        }

        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        binding.tvLogin2.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun navigateToHome() {
        startActivity(Intent(this, UserActivity::class.java))
        finish()
    }

    private fun showError(message: String) {
        binding.progressBar.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
