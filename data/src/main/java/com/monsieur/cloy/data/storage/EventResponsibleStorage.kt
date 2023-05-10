package com.monsieur.cloy.data.storage

import com.monsieur.cloy.data.db.dao.EventResponsibleDao
import com.monsieur.cloy.data.storage.models.EventResponsibleEntity
import kotlinx.coroutines.flow.Flow

class EventResponsibleStorage(private val eventResponsibleDao: EventResponsibleDao) {

    suspend fun insertEventResponsibles(eventResponsibles: List<EventResponsibleEntity>){
        eventResponsibleDao.insertEventResponsibles(eventResponsibles)
    }

    fun getEventResponsiblesFlow(): Flow<List<EventResponsibleEntity>>{
        return eventResponsibleDao.getEventResponsiblesFlow()
    }

    suspend fun getEventResponsibles(): List<EventResponsibleEntity>{
        return eventResponsibleDao.getEventResponsibles()
    }

    suspend fun deleteAllEventResponsibles(){
        eventResponsibleDao.deleteAllEventResponsibles()
    }
}