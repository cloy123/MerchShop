package com.monsieur.cloy.data.repository

import com.monsieur.cloy.data.api.MerchShopApi
import com.monsieur.cloy.data.mappers.EventMapper
import com.monsieur.cloy.data.mappers.EventParticipantMapper
import com.monsieur.cloy.data.mappers.EventResponsibleMapper
import com.monsieur.cloy.data.mappers.EventRoleMapper
import com.monsieur.cloy.data.storage.EventStorage
import com.monsieur.cloy.domain.models.*
import com.monsieur.cloy.domain.models.common.UpdateEventDataResult
import com.monsieur.cloy.domain.models.common.UpdateProductDataResult
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
        var roles = ArrayList<EventRole>()
        var responsibles = ArrayList<EventResponsible>()
        var participants = ArrayList<EventParticipant>()
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
}