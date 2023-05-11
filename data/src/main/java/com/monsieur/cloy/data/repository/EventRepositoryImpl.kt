package com.monsieur.cloy.data.repository

import com.monsieur.cloy.data.api.MerchShopApi
import com.monsieur.cloy.data.api.models.FinishParticipantDto
import com.monsieur.cloy.data.api.models.requests.FinishEventRequest
import com.monsieur.cloy.data.api.models.requests.SignupEventRequest
import com.monsieur.cloy.data.mappers.EventMapper
import com.monsieur.cloy.data.mappers.EventParticipantMapper
import com.monsieur.cloy.data.mappers.EventResponsibleMapper
import com.monsieur.cloy.data.mappers.EventRoleMapper
import com.monsieur.cloy.data.storage.EventStorage
import com.monsieur.cloy.domain.models.*
import com.monsieur.cloy.domain.models.common.FinishEventResult
import com.monsieur.cloy.domain.models.common.SignupEventResult
import com.monsieur.cloy.domain.models.common.UpdateEventDataResult
import com.monsieur.cloy.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EventRepositoryImpl(private val eventStorage: EventStorage, private val merchShopApi: MerchShopApi): EventRepository {

    private val eventMapper = EventMapper()
    private val eventRoleMapper = EventRoleMapper()
    private val eventParticipantMapper = EventParticipantMapper()
    private val eventResponsibleMapper = EventResponsibleMapper()

    override suspend fun getEvents(): List<Event> {
        return eventStorage.getEvents().map { eventMapper.toDomainModel(it) }
    }

    override fun getEventsFlow(): Flow<List<Event>> {
        return eventStorage.getEventsFlow().map { l -> l.map { eventMapper.toDomainModel(it) } }
    }

    override suspend fun deleteAllData() {
        eventStorage.deleteAllEvents()
    }

    override suspend fun updateEventData(accessToken: String): UpdateEventDataResult {
        var isSuccessful: Boolean
        var events: List<Event>?
        val roles = ArrayList<EventRole>()
        val responsibles = ArrayList<EventResponsible>()
        val participants = ArrayList<EventParticipant>()
        var code: Int
        try {
            val response = merchShopApi.getEventsInfo(accessToken)
            isSuccessful = response.isSuccessful
            code = response.code()
            if (response.isSuccessful && response.body() != null) {
                for(event in response.body()!!.events){
                    roles.addAll(event.eventRoles.map { eventRoleMapper.roleDtoToRole(it) })
                    responsibles.addAll(event.eventResponsibles.map { eventResponsibleMapper.responsibleDtoToResponsible(it) })
                    participants.addAll(event.eventParticipants.map { eventParticipantMapper.participantDtoToParticipant(it, roles.find { r -> r.id == it.eventRoleId }!!) })
                }
                events = response.body()!!.events.map { eventMapper.eventDtoToEvent(it) }
            } else {
                events = null
            }
        } catch (e: Exception) {
            code = -1
            isSuccessful = false
            events = null
        }
        return UpdateEventDataResult(events, responsibles, roles, participants, isSuccessful, code)
    }

    override suspend fun insertEvents(events: List<Event>) {
        eventStorage.insertEvents(events.map { eventMapper.toDataModel(it) })
    }

    override suspend fun finishEvent(
        accessToken: String,
        eventId: String,
        participants: List<EventParticipant>
    ): FinishEventResult {
        var isSuccessful: Boolean
        var code: Int

        var isFinished = false
        var errorMessage = ""

        val request = FinishEventRequest(eventId, participants.map { FinishParticipantDto(it.id, it.isVisit) })

        try {
            val response = merchShopApi.finishEvent(accessToken, request)
            isSuccessful = response.isSuccessful
            code = response.code()
            if (response.isSuccessful && response.body() != null) {
                isFinished = response.body()!!.isFinished
                errorMessage = response.body()!!.errorMessage
            }
        } catch (e: Exception) {
            code = -1
            isSuccessful = false
        }
        return FinishEventResult(isSuccessful, isFinished, errorMessage, code)
    }

    override suspend fun signupEvent(
        accessToken: String,
        eventId: String,
        eventRoleId: String
    ): SignupEventResult {
        var isSuccessful: Boolean
        var code: Int

        var isSigned = false
        var errorMessage = ""

        val request = SignupEventRequest(eventId, eventRoleId)

        try {
            val response = merchShopApi.signupEvent(accessToken, request)
            isSuccessful = response.isSuccessful
            code = response.code()
            if (response.isSuccessful && response.body() != null) {
                isSigned = response.body()!!.isSigned
                errorMessage = response.body()!!.errorMessage
            }
        } catch (e: Exception) {
            code = -1
            isSuccessful = false
        }
        return SignupEventResult(isSuccessful, isSigned, errorMessage, code)
    }
}