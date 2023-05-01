package com.monsieur.cloy.data.repository

import com.monsieur.cloy.data.api.MerchShopApi
import com.monsieur.cloy.data.api.models.requests.LoginRequest
import com.monsieur.cloy.data.mappers.UserMapper
import com.monsieur.cloy.data.storage.UserStorage
import com.monsieur.cloy.domain.models.LoginParam
import com.monsieur.cloy.domain.models.LoginResult
import com.monsieur.cloy.domain.models.User
import com.monsieur.cloy.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val merchShopApi: MerchShopApi,
    private val userStorage: UserStorage
): UserRepository {


    val userMapper = UserMapper()

    override suspend fun updateUser(user: User) {
        userStorage.updateUser(userMapper.toDataModel(user))
    }

    override suspend fun insertUser(user: User) {
        userStorage.insertUser(userMapper.toDataModel(user))
    }

    override fun getUser(): Flow<List<User>> {
        return userStorage.getUser().map { list ->
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
        try {
            val response = merchShopApi.login(loginRequest)
            if(response.isSuccessful && response.body() != null){

            }
        }catch (e: Exception){

        }
        return LoginResult()
    }
}