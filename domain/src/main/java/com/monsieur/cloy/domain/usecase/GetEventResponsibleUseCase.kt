package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.models.EventResponsible
import com.monsieur.cloy.domain.repository.EventResponsibleRepository
import kotlinx.coroutines.flow.Flow

class GetEventResponsibleUseCase (private val eventResponsibleRepository: EventResponsibleRepository) {
    fun execute(): Flow<List<EventResponsible>> {
        return eventResponsibleRepository.getEventResponsiblesFlow()
    }
}