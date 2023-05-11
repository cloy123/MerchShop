package com.monsieur.cloy.domain.repository

import com.monsieur.cloy.domain.models.common.GetUserInfoResult
import com.monsieur.cloy.domain.models.common.LoginParam
import com.monsieur.cloy.domain.models.common.LoginResult
import com.monsieur.cloy.domain.models.User
import com.monsieur.cloy.domain.models.common.RefreshTokenResult
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun updateUser(user: User)

    suspend fun insertUser(user: User)

    fun getUserFlow(): Flow<List<User>>

    suspend fun deleteUser()

    suspend fun login(loginParam: LoginParam) : LoginResult

    suspend fun getUserInfo(accessToken: String) : GetUserInfoResult

    suspend fun getUser(): List<User>

    suspend fun refreshToken(accessToken: String, refreshToken: String): RefreshTokenResult

    suspend fun logout(accessToken: String, refreshToken: String)
}