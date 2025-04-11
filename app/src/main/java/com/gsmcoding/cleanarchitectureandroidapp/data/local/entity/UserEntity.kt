package com.gsmcoding.cleanarchitectureandroidapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gsmcoding.cleanarchitectureandroidapp.domain.model.User

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val uid: String = "",
    val name: String = "",
    val email: String = "",
    val profileUrl: String = ""
) {
    fun toDomain() = User(uid.toString(), name, email)
    companion object {
        fun fromDomain(user: User) = UserEntity(user.uid, user.name, user.email)
    }
}