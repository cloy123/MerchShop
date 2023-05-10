package com.monsieur.cloy.data.storage

import com.monsieur.cloy.data.db.dao.EventDao
import com.monsieur.cloy.data.storage.models.EventEntity
import kotlinx.coroutines.flow.Flow

class EventStorage(private val eventDao: EventDao) {

    suspend fun insertEvents(events: List<EventEntity>){
        eventDao.insertEvents(events)
    }

    fun getEventsFlow(): Flow<List<EventEntity>>{
        return eventDao.getEventsFlow()
    }

    suspend fun getEvents(): List<EventEntity>{
        return eventDao.getEvents()
    }

    suspend fun deleteAllEvents(){
        eventDao.deleteAllEvents()
    }
}