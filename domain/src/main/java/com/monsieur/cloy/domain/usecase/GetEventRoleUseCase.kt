package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.models.EventResponsible
import com.monsieur.cloy.domain.models.EventRole
import com.monsieur.cloy.domain.repository.EventResponsibleRepository
import com.monsieur.cloy.domain.repository.EventRoleRepository
import kotlinx.coroutines.flow.Flow

class GetEventRoleUseCase (private val eventRoleRepository: EventRoleRepository) {
    fun execute(): Flow<List<EventRole>> {
        return eventRoleRepository.getEventRolesFlow()
    }
}