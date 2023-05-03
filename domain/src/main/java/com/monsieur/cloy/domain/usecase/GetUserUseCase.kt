package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.models.User
import com.monsieur.cloy.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetUserUseCase(private val userRepository: UserRepository) {
    fun execute(): Flow<List<User>> {
        return userRepository.getUserFlow()
    }
}