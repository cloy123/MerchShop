package com.monsieur.cloy.data.mappers

import com.monsieur.cloy.data.storage.models.EventResponsibleEntity
import com.monsieur.cloy.data.storage.models.EventRoleEntity
import com.monsieur.cloy.domain.models.EventResponsible
import com.monsieur.cloy.domain.models.EventRole

class EventRoleMapper {

    fun toDomainModel(eventRoleEntity: EventRoleEntity): EventRole {
        return EventRole(
            eventRoleEntity.id,
            eventRoleEntity.name,
            eventRoleEntity.userTypeId,
            eventRoleEntity.eventId,
            eventRoleEntity.prize
        )
    }

    fun toDataModel(eventRole: EventRole): EventRoleEntity {
        val entity = EventRoleEntity()
        entity.id = eventRole.id
        entity.name = eventRole.name
        entity.userTypeId = eventRole.userTypeId
        entity.eventId = eventRole.eventId
        entity.prize = eventRole.prize
        return entity
    }
}