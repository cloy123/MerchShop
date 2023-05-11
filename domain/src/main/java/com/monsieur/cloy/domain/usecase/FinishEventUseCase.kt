package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.models.EventParticipant
import com.monsieur.cloy.domain.models.common.FinishEventResult
import com.monsieur.cloy.domain.repository.EventRepository
import com.monsieur.cloy.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FinishEventUseCase (private val userRepository: UserRepository,
                          private val eventRepository: EventRepository
) {
    suspend fun execute(eventId: String, paticipants: List<EventParticipant>): Flow<FinishEventResult> {
        return flow {

            val userList = userRepository.getUser()
            if (userList.isEmpty()) {
                emit(FinishEventResult(false, false, "", -1))
            }
            val user = userList[0]


            var result = eventRepository.finishEvent(user.accessToken, eventId, paticipants)
            if (result.code == 401) {
                val refreshTokenResult =
                    userRepository.refreshToken(user.accessToken, user.refreshToken)
                if (refreshTokenResult.isSuccessful) {
                    user.accessToken = refreshTokenResult.accessToken!!
                    user.refreshToken = refreshTokenResult.refreshToken!!
                    userRepository.updateUser(user)
                } else if (refreshTokenResult.code == 401) {
                    userRepository.deleteUser()
                    emit(FinishEventResult(false, false, "", 401))
                } else {
                    emit(FinishEventResult(false, false, "", -1))
                }
            }
            result = eventRepository.finishEvent(user.accessToken, eventId, paticipants)

            emit(result)
        }
    }
}