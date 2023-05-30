package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.models.common.LoginParam
import com.monsieur.cloy.domain.models.common.LoginResult
import com.monsieur.cloy.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginUseCase(private val userRepository: UserRepository) {

    suspend fun execute(loginParam: LoginParam): Flow<LoginResult> {
        return flow {
            val result = userRepository.login(loginParam)
            if(result.user != null){
                userRepository.insertUser(result.user)
            }
            emit(result)
        }
    }
}