package com.monsieur.cloy.data.storage

import com.monsieur.cloy.data.db.dao.EventResponsibleDao
import com.monsieur.cloy.data.storage.models.EventResponsibleEntity
import kotlinx.coroutines.flow.Flow

class EventResponsibleStorage(private val eventResponsibleDao: EventResponsibleDao) {

    suspend fun insertEventResponsibles(eventResponsibles: List<EventResponsibleEntity>){
        eventResponsibleDao.insertEventResponsibles(eventResponsibles)
    }

    fun getAllEventResponsibles(): Flow<List<EventResponsibleEntity>>{
        return eventResponsibleDao.getAllEventResponsibles()
    }

    fun deleteAllEventResponsibles(){
        eventResponsibleDao.deleteAllEventResponsibles()
    }
}