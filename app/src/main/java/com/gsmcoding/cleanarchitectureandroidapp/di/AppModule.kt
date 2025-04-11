package com.gsmcoding.cleanarchitectureandroidapp.di

import android.content.Context
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.gsmcoding.cleanarchitectureandroidapp.data.api.ApiService
import com.gsmcoding.cleanarchitectureandroidapp.data.local.db.AppDatabase
import com.gsmcoding.cleanarchitectureandroidapp.data.local.dao.UserDao
import com.gsmcoding.cleanarchitectureandroidapp.data.remote.FirebaseUserService
import com.gsmcoding.cleanarchitectureandroidapp.data.repository.UserRepositoryImpl
import com.gsmcoding.cleanarchitectureandroidapp.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

//@Module
//@InstallIn(SingletonComponent::class)
//object AppModule {
//
//    @Provides
//    fun provideBaseUrl() = "https://jsonplaceholder.typicode.com/"
//
//    @Provides
//    @Singleton
//    fun provideApiService(baseUrl: String): ApiService =
//        Retrofit.Builder()
//            .baseUrl(baseUrl)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(ApiService::class.java)
//
//    @Provides
//    @Singleton
//    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
//        Room.databaseBuilder(context, AppDatabase::class.java, "app_db").build()
//
//    @Provides
//    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()
//}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "app.db").build()

    @Provides
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()

    @Provides
    fun provideFirebaseService(
        firestore: FirebaseFirestore,
        auth: FirebaseAuth
    ): FirebaseUserService = FirebaseUserService(firestore, auth)

    @Provides
    fun provideUserRepository(
        userDao: UserDao,
        service: FirebaseUserService
    ): UserRepository = UserRepositoryImpl(userDao, service)
}
