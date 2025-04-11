package com.gsmcoding.cleanarchitectureandroidapp.presentation.user.ui

import UserAdapter
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.gsmcoding.cleanarchitectureandroidapp.databinding.ActivityUserBinding
import com.gsmcoding.cleanarchitectureandroidapp.presentation.user.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
//class UserActivity : AppCompatActivity() {
//
//    private val viewModel: UserViewModel by viewModels()
//    private lateinit var userAdapter: UserAdapter
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_user)
//
//        userAdapter = UserAdapter()
//        findViewById<RecyclerView>(R.id.userRecyclerView).adapter = userAdapter
//
//
//        lifecycleScope.launchWhenStarted {
//            viewModel.users.collect { users ->
//                // update UI
//                Log.d("UserActivity", "Users: $users")
//                userAdapter.submitList(users)
//            }
//        }
//    }
//}

@AndroidEntryPoint
class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding
    private val viewModel: UserViewModel by viewModels()
    private val adapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewUsers.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewUsers.adapter = adapter

//        lifecycleScope.launchWhenStarted {
//            viewModel.users.collect { users ->
//                adapter.submitList(users)
//            }
//        }

        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { state ->
                when (state) {
                    is UserUiState.Loading -> {
                        binding.progressBar.isVisible = true
                        binding.textError.isVisible = false
                        binding.recyclerViewUsers.isVisible = false
                    }
                    is UserUiState.Success -> {
                        binding.progressBar.isVisible = false
                        binding.textError.isVisible = false
                        binding.recyclerViewUsers.isVisible = true
                        adapter.submitList(state.users)
                    }
                    is UserUiState.Error -> {
                        binding.progressBar.isVisible = false
                        binding.textError.isVisible = true
                        binding.recyclerViewUsers.isVisible = false
                        binding.textError.text = state.message
                    }
                }
            }
        }

    }
}

