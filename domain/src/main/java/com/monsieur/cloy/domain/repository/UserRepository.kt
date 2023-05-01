package com.monsieur.cloy.domain.repository

import com.monsieur.cloy.domain.models.LoginParam
import com.monsieur.cloy.domain.models.LoginResult
import com.monsieur.cloy.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun updateUser(user: User)

    suspend fun insertUser(user: User)

    fun getUser(): Flow<List<User>>

    suspend fun deleteUser()

    suspend fun login(loginParam: LoginParam) : LoginResult
}