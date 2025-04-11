package com.gsmcoding.cleanarchitectureandroidapp.presentation.auth.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.lifecycle.lifecycleScope
import com.gsmcoding.cleanarchitectureandroidapp.R
import com.gsmcoding.cleanarchitectureandroidapp.databinding.ActivityLoginBinding
import com.gsmcoding.cleanarchitectureandroidapp.presentation.auth.validators.InputValidator
import com.gsmcoding.cleanarchitectureandroidapp.presentation.auth.viewmodel.LoginViewModel
import com.gsmcoding.cleanarchitectureandroidapp.presentation.user.ui.UserActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updatePadding(
                top = systemBars.top
            )
            insets
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (!InputValidator.isEmailValid(email)) {
                binding.layoutEmail.error = "Invalid email format"
                return@setOnClickListener
            } else binding.layoutEmail.error = null

            if (!InputValidator.isPasswordValid(password)) {
                binding.layoutPassword.error = "Password must be at least 6 characters"
                return@setOnClickListener
            } else binding.layoutPassword.error = null

            viewModel.login(email, password)
        }

        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { state ->
                when (state) {
                    is AuthUiState.Loading -> showLoading()
                    is AuthUiState.Success -> navigateToHome()
                    is AuthUiState.Error -> showError(state.message)
                    else -> Unit
                }
            }
        }

        binding.tvSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
        binding.tvSignup2.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun showError(msg: String) {
        binding.progressBar.visibility = View.GONE
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToHome() {
        binding.progressBar.visibility = View.GONE
        startActivity(Intent(this, UserActivity::class.java))
        finish()
    }
}
