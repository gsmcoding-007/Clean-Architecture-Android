package com.gsmcoding.cleanarchitectureandroidapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

data class User(
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val profileUrl: String = ""
)

//@Entity(tableName = "users")
//data class User(
//    @PrimaryKey val id: String = UUID.randomUUID().toString(),
//    val name: String,
//    val email: String,
//    val profileUrl: String? = null
//)
