package com.monsieur.cloy.data.repository

import com.monsieur.cloy.data.mappers.EventParticipantMapper
import com.monsieur.cloy.data.storage.EventParticipantStorage
import com.monsieur.cloy.domain.models.EventParticipant
import com.monsieur.cloy.domain.repository.EventParticipantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EventParticipantRepositoryImpl(private val eventParticipantStorage: EventParticipantStorage): EventParticipantRepository {

    private val eventParticipantMapper = EventParticipantMapper()

    override suspend fun getEventParticipants(): List<EventParticipant> {
        return eventParticipantStorage.getEventParticipants().map { eventParticipantMapper.toDomainModel(it) }
    }

    override fun getEventParticipantsFlow(): Flow<List<EventParticipant>> {
        return eventParticipantStorage.getAllEventParticipantsFlow().map { list -> list.map { eventParticipantMapper.toDomainModel(it) } }
    }

    override suspend fun deleteAllData() {
        eventParticipantStorage.deleteAllEventParticipants()
    }

    override suspend fun insertEventParticipants(eventParticipants: List<EventParticipant>) {
        eventParticipantStorage.insertEventParticipants(eventParticipants.map { eventParticipantMapper.toDataModel(it) })
    }
}