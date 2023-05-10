package com.monsieur.cloy.data.repository

import com.monsieur.cloy.data.mappers.EventRoleMapper
import com.monsieur.cloy.data.storage.EventRoleStorage
import com.monsieur.cloy.domain.models.EventRole
import com.monsieur.cloy.domain.repository.EventRoleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EventRoleRepositoryImpl(private val eventRoleStorage: EventRoleStorage): EventRoleRepository {

    private val eventRoleMapper = EventRoleMapper()

    override suspend fun getEventRoles(): List<EventRole> {
        return eventRoleStorage.getEventRoles().map { eventRoleMapper.toDomainModel(it) }
    }

    override fun getEventRolesFlow(): Flow<List<EventRole>> {
        return  eventRoleStorage.getEventRolesFlow().map { list -> list.map { eventRoleMapper.toDomainModel(it) } }
    }

    override suspend fun deleteAllData() {
        eventRoleStorage.deleteAllEventRoles()
    }

    override suspend fun insertEventRoles(eventRoles: List<EventRole>) {
        eventRoleStorage.insertEventRoles(eventRoles.map { eventRoleMapper.toDataModel(it) })
    }
}