package com.monsieur.cloy.data.mappers

import com.monsieur.cloy.data.storage.models.EventEntity
import com.monsieur.cloy.data.storage.models.EventParticipantEntity
import com.monsieur.cloy.data.storage.models.EventParticipantWithRole
import com.monsieur.cloy.domain.models.Event
import com.monsieur.cloy.domain.models.EventParticipant
import com.monsieur.cloy.domain.models.EventRole

class EventParticipantMapper {

    fun toDomainModel(eventParticipantWithRole: EventParticipantWithRole): EventParticipant {
        var eventRole = EventRole(
            eventParticipantWithRole.eventRoleId,
            eventParticipantWithRole.roleName,
            eventParticipantWithRole.userTypeId,
            eventParticipantWithRole.eventId,
            eventParticipantWithRole.prize
        )
        return EventParticipant(
            eventParticipantWithRole.id,
            eventParticipantWithRole.eventId,
            eventParticipantWithRole.userId,
            eventParticipantWithRole.isVisit,
            eventParticipantWithRole.userTypeId,
            eventParticipantWithRole.firstName,
            eventParticipantWithRole.lastName,
            eventParticipantWithRole.email,
            eventParticipantWithRole.className,
            eventParticipantWithRole.genderId,
            eventRole
        )
    }

    fun toDataModel(eventParticipant: EventParticipant): EventParticipantEntity {
        val entity = EventParticipantEntity()
        entity.id = eventParticipant.id
        entity.eventId = eventParticipant.eventId
        entity.isVisit = eventParticipant.isVisit
        entity.userTypeId = eventParticipant.userTypeId
        entity.firstName = eventParticipant.firstName
        entity.lastName = eventParticipant.lastName
        entity.email = eventParticipant.email
        entity.className = eventParticipant.className
        entity.genderId = eventParticipant.genderId
        entity.eventRoleId = eventParticipant.role.id
        return entity
    }
}