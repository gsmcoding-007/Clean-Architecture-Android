package com.gsmcoding.cleanarchitectureandroidapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gsmcoding.cleanarchitectureandroidapp.data.local.dao.UserDao
import com.gsmcoding.cleanarchitectureandroidapp.data.local.entity.UserEntity
import com.gsmcoding.cleanarchitectureandroidapp.domain.model.User

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}