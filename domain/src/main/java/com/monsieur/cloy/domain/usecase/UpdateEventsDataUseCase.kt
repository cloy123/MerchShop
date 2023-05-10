package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.models.common.UpdateEventDataResult
import com.monsieur.cloy.domain.models.common.UpdateProductDataResult
import com.monsieur.cloy.domain.repository.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UpdateEventsDataUseCase(
    private val eventRepository: EventRepository,
    private val eventRoleRepository: EventRoleRepository,
    private val eventParticipantRepository: EventParticipantRepository,
    private val eventResponsibleRepository: EventResponsibleRepository,
    private val userRepository: UserRepository
) {
    suspend fun execute(): Flow<UpdateEventDataResult> {
        return flow {

            val userList = userRepository.getUser()
            if (userList.isEmpty()) {
                emit(UpdateEventDataResult(null, null, null, null, false, -1))
            }
            val user = userList[0]

            var result = eventRepository.updateEventData(user.accessToken)
            if (result.code == 401) {
                var refreshTokenResult =
                    userRepository.refreshToken(user.accessToken, user.refreshToken)
                if (refreshTokenResult.isSuccessful) {
                    user.accessToken = refreshTokenResult.accessToken!!
                    user.refreshToken = refreshTokenResult.refreshToken!!
                    userRepository.updateUser(user)
                } else if (refreshTokenResult.code == 401) {
                    userRepository.deleteUser()
                    emit(UpdateEventDataResult(null, null, null, null, false, 401))
                } else {
                    emit(UpdateEventDataResult(null, null, null, null, false, -1))
                }
            }
            result = eventRepository.updateEventData(user.accessToken)
            if (result.events != null) {
                eventRepository.deleteAllData()
                eventParticipantRepository.deleteAllData()
                eventResponsibleRepository.deleteAllData()
                eventRoleRepository.deleteAllData()
                eventRepository.insertEvents(result.events!!)
                eventParticipantRepository.insertEventParticipants(result.eventParticipants!!)
                eventResponsibleRepository.insertEventResponsibles(result.eventResponsibles!!)
                eventRoleRepository.insertEventRoles(result.eventRoles!!)
            }
            emit(result)
        }
    }
}