package com.monsieur.cloy.data.mappers

import com.monsieur.cloy.data.api.models.EventDto
import com.monsieur.cloy.data.api.models.EventRoleDto
import com.monsieur.cloy.data.storage.models.CurrencyTransactionEntity
import com.monsieur.cloy.data.storage.models.EventEntity
import com.monsieur.cloy.domain.models.CurrencyTransaction
import com.monsieur.cloy.domain.models.Event
import com.monsieur.cloy.domain.models.EventRole
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class EventMapper {

    fun toDomainModel(eventEntity: EventEntity): Event {
        return Event(
            eventEntity.id,
            eventEntity.date,
            eventEntity.name,
            eventEntity.description,
            eventEntity.availableFor
        )
    }

    fun toDataModel(event: Event): EventEntity {
        val entity = EventEntity()
        entity.id = event.id
        entity.date = event.date
        entity.name = event.name
        entity.description = event.description
        entity.availableFor = event.availableFor
        return entity
    }

    fun eventDtoToEvent(eventDto: EventDto): Event {
        return Event(
            eventDto.id,
            LocalDateTime.parse(eventDto.date, DateTimeFormatter.ISO_DATE_TIME),
            eventDto.name,
            eventDto.description,
            eventDto.availableFor
        )
    }
}