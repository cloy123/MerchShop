package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.models.common.SignupEventResult
import com.monsieur.cloy.domain.repository.EventRepository
import com.monsieur.cloy.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SignupEventUseCase (private val userRepository: UserRepository,
                          private val eventRepository: EventRepository
) {
    suspend fun execute(eventId: String, eventRoleId: String): Flow<SignupEventResult> {
        return flow {

            val userList = userRepository.getUser()
            if (userList.isEmpty()) {
                emit(SignupEventResult(false, false, "", -1))
            }
            val user = userList[0]


            var result = eventRepository.signupEvent(user.accessToken, eventId, eventRoleId)
            if (result.code == 401) {
                val refreshTokenResult =
                    userRepository.refreshToken(user.accessToken, user.refreshToken)
                if (refreshTokenResult.isSuccessful) {
                    user.accessToken = refreshTokenResult.accessToken!!
                    user.refreshToken = refreshTokenResult.refreshToken!!
                    userRepository.updateUser(user)
                } else if (refreshTokenResult.code == 401) {
                    userRepository.deleteUser()
                    emit(SignupEventResult(false, false, "", 401))
                } else {
                    emit(SignupEventResult(false, false, "", -1))
                }
            }
            result = eventRepository.signupEvent(user.accessToken, eventId, eventRoleId)

            emit(result)
        }
    }
}