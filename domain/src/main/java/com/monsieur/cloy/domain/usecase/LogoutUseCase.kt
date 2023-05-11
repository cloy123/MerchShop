package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.repository.UserRepository

class LogoutUseCase (private val userRepository: UserRepository) {
    suspend fun execute() {

            val userList = userRepository.getUser()
            if (!userList.isEmpty()) {
                val user = userList[0]
                userRepository.logout(user.accessToken, user.refreshToken)
            }
            userRepository.deleteUser()
    }
}