package com.monsieur.cloy.data.mappers

import com.monsieur.cloy.data.storage.models.EventEntity
import com.monsieur.cloy.data.storage.models.EventResponsibleEntity
import com.monsieur.cloy.domain.models.Event
import com.monsieur.cloy.domain.models.EventResponsible

class EventResponsibleMapper {

    fun toDomainModel(eventResponsibleEntity: EventResponsibleEntity): EventResponsible {
        return EventResponsible(
            eventResponsibleEntity.id,
            eventResponsibleEntity.eventId,
            eventResponsibleEntity.userId,
            eventResponsibleEntity.userTypeId,
            eventResponsibleEntity.firstName,
            eventResponsibleEntity.lastName,
            eventResponsibleEntity.email,
            eventResponsibleEntity.className,
            eventResponsibleEntity.genderId
        )
    }

    fun toDataModel(eventResponsible: EventResponsible): EventResponsibleEntity {
        val entity = EventResponsibleEntity()
        entity.id = eventResponsible.id
        entity.eventId = eventResponsible.eventId
        entity.userId = eventResponsible.userId
        entity.userTypeId = eventResponsible.userTypeId
        entity.firstName = eventResponsible.firstName
        entity.lastName = eventResponsible.lastName
        entity.email = eventResponsible.email
        entity.className = eventResponsible.className
        entity.genderId = eventResponsible.genderId
        return entity
    }
}