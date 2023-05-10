package com.monsieur.cloy.data.repository

import com.monsieur.cloy.data.mappers.EventResponsibleMapper
import com.monsieur.cloy.data.storage.EventResponsibleStorage
import com.monsieur.cloy.domain.models.EventResponsible
import com.monsieur.cloy.domain.repository.EventResponsibleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EventResponsibleRepositoryImpl(private val eventResponsibleStorage: EventResponsibleStorage): EventResponsibleRepository {

    private val eventResponsibleMapper = EventResponsibleMapper()

    override suspend fun getEventResponsibles(): List<EventResponsible> {
        return eventResponsibleStorage.getEventResponsibles().map { eventResponsibleMapper.toDomainModel(it) }
    }

    override fun getEventResponsiblesFlow(): Flow<List<EventResponsible>> {
        return eventResponsibleStorage.getEventResponsiblesFlow().map { list -> list.map { eventResponsibleMapper.toDomainModel(it) } }
    }

    override suspend fun deleteAllData() {
        eventResponsibleStorage.deleteAllEventResponsibles()
    }

    override suspend fun insertEventResponsibles(eventResponsibles: List<EventResponsible>) {
        eventResponsibleStorage.insertEventResponsibles(eventResponsibles.map { eventResponsibleMapper.toDataModel(it) })
    }
}