package com.monsieur.cloy.data.repository

import com.monsieur.cloy.data.api.MerchShopApi
import com.monsieur.cloy.data.api.models.requests.LoginRequest
import com.monsieur.cloy.data.api.models.requests.RefreshTokenRequest
import com.monsieur.cloy.data.mappers.UserMapper
import com.monsieur.cloy.data.storage.UserStorage
import com.monsieur.cloy.domain.models.User
import com.monsieur.cloy.domain.models.common.*
import com.monsieur.cloy.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val merchShopApi: MerchShopApi,
    private val userStorage: UserStorage
) : UserRepository {


    val userMapper = UserMapper()

    override suspend fun updateUser(user: User) {
        userStorage.updateUser(userMapper.toDataModel(user))
    }

    override suspend fun insertUser(user: User) {
        userStorage.insertUser(userMapper.toDataModel(user))
    }

    override fun getUserFlow(): Flow<List<User>> {
        return userStorage.getUserFlow().map { list ->
            list.map {
                userMapper.toDomainModel(it)
            }
        }
    }

    override suspend fun deleteUser() {
        userStorage.deleteUser()
    }

    override suspend fun login(loginParam: LoginParam): LoginResult {
        val loginRequest = LoginRequest(loginParam.email, loginParam.password)
        var isSuccessful: Boolean
        var user: User?
        var isUserFound: Boolean
        var isPasswordCorrect: Boolean
        var isAccess: Boolean
//        try {
            val response = merchShopApi.login(loginRequest)
            isSuccessful = response.isSuccessful
            if (response.isSuccessful && response.body() != null) {
                isUserFound = response.body()!!.isUserFound
                isPasswordCorrect = response.body()!!.isPasswordCorrect
                isAccess = response.body()!!.isAccess
                user = userMapper.loginResponseToDomainModel(response.body()!!)
            } else {
                isUserFound = false
                isPasswordCorrect = false
                isAccess = false
                user = null
            }
//        } catch (e: Exception) {
//            isSuccessful = false
//            isUserFound = false
//            isPasswordCorrect = false
//            isAccess = false
//            user = null
//        }
        return LoginResult(
            user,
            isUserFound,
            isPasswordCorrect,
            isAccess,
            isSuccessful
        )
    }

    override suspend fun getUserInfo(accessToken: String): GetUserInfoResult {
        var isSuccessful: Boolean
        var userInfo: UserInfo?
        var code: Int
        try {
            val response = merchShopApi.getUserInfo(accessToken)
            isSuccessful = response.isSuccessful
            code = response.code()
            if (response.isSuccessful && response.body() != null) {
                userInfo = userMapper.getUserInfoResponseToUserInfo(response.body()!!)
            } else {
                userInfo = null
            }
        } catch (e: Exception) {
            code = -1
            isSuccessful = false
            userInfo = null
        }
        return GetUserInfoResult(userInfo, isSuccessful, code)
    }

    override suspend fun getUser(): List<User> {
        return userStorage.getUser().map {
            userMapper.toDomainModel(it)
        }
    }

    override suspend fun refreshToken(accessToken: String, refreshToken: String): RefreshTokenResult {
        var isSuccessful: Boolean
        var newAccessToken: String?
        var newRefreshToken: String?
        var code: Int
        try {
            val response = merchShopApi.refreshToken(RefreshTokenRequest(accessToken, refreshToken))
            isSuccessful = response.isSuccessful
            code = response.code()
            if (response.isSuccessful && response.body() != null) {
                newAccessToken = response.body()!!.accessToken
                newRefreshToken = response.body()!!.refreshToken
            } else {
                newAccessToken = null
                newRefreshToken = null
            }
        } catch (e: Exception) {
            code = -1
            isSuccessful = false
            newAccessToken = null
            newRefreshToken = null
        }
        return RefreshTokenResult(newAccessToken, newRefreshToken, isSuccessful, code)
    }
}