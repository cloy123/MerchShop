package com.monsieur.cloy.data.mappers

import com.monsieur.cloy.data.api.models.EventRoleDto
import com.monsieur.cloy.data.storage.models.EventRoleEntity
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

    fun roleDtoToRole(roleDto: EventRoleDto): EventRole {
        return EventRole(
            roleDto.id,
            roleDto.name,
            roleDto.userTypeId,
            roleDto.eventId,
            roleDto.prize
        )
    }
}