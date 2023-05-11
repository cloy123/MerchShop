package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.models.EventParticipant
import com.monsieur.cloy.domain.repository.EventParticipantRepository
import kotlinx.coroutines.flow.Flow

class GetEventParticipantsUseCase (private val eventParticipantRepository: EventParticipantRepository) {
    fun execute(): Flow<List<EventParticipant>> {
        return eventParticipantRepository.getEventParticipantsFlow()
    }
}