package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.models.EventRole
import com.monsieur.cloy.domain.repository.EventRoleRepository
import kotlinx.coroutines.flow.Flow

class GetEventRolesUseCase (private val eventRoleRepository: EventRoleRepository) {
    fun execute(): Flow<List<EventRole>> {
        return eventRoleRepository.getEventRolesFlow()
    }
}