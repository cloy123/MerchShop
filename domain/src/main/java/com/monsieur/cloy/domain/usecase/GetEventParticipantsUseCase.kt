package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.models.Event
import com.monsieur.cloy.domain.models.EventParticipant
import com.monsieur.cloy.domain.repository.EventParticipantRepository
import com.monsieur.cloy.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow

class GetEventParticipantsUseCase (private val eventParticipantRepository: EventParticipantRepository) {
    fun execute(): Flow<List<EventParticipant>> {
        return eventParticipantRepository.getEventParticipantsFlow()
    }
}