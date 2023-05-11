package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.models.Event
import com.monsieur.cloy.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow

class GetEventsUseCase (private val eventRepository: EventRepository) {
    fun execute(): Flow<List<Event>> {
        return eventRepository.getEventsFlow()
    }
}