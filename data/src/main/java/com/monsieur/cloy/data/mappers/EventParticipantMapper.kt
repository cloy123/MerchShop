package com.monsieur.cloy.data.mappers

import com.monsieur.cloy.data.api.models.EventParticipantDto
import com.monsieur.cloy.data.storage.models.EventParticipantEntity
import com.monsieur.cloy.data.storage.models.EventParticipantWithRole
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

    fun participantDtoToParticipant(participantDto: EventParticipantDto, eventRole: EventRole): EventParticipant {

        var className: String? = ""
        if(participantDto.classNumber != null){
            className += participantDto.classNumber.toString()
        }
        if(participantDto.classLetter != null){
            className += participantDto.classLetter
        }
        if(className == ""){
            className = null
        }

        return EventParticipant(
            participantDto.id,
            participantDto.eventId,
            participantDto.userId,
            participantDto.isVisit,
            participantDto.userTypeId,
            participantDto.firstName,
            participantDto.lastName,
            participantDto.email,
            className,
            participantDto.genderId,
            eventRole
        )
    }
}