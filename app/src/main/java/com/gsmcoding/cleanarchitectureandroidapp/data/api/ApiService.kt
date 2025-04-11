package com.gsmcoding.cleanarchitectureandroidapp.data.api

import com.gsmcoding.cleanarchitectureandroidapp.data.model.UserDto
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<UserDto>
}