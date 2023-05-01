package com.monsieur.cloy.data.storage

import com.monsieur.cloy.data.db.dao.EventParticipantDao
import com.monsieur.cloy.data.storage.models.EventParticipantEntity
import com.monsieur.cloy.data.storage.models.EventParticipantWithRole
import kotlinx.coroutines.flow.Flow

class EventParticipantStorage(private val eventParticipantDao: EventParticipantDao) {
    suspend fun insertEventParticipants(eventParticipants: List<EventParticipantEntity>){
        eventParticipantDao.insertEventParticipants(eventParticipants)
    }

    fun getAllEventParticipants(): Flow<List<EventParticipantWithRole>>{
        return eventParticipantDao.getAllEventParticipants()
    }

    fun deleteAllEventParticipants(){
        eventParticipantDao.deleteAllEventParticipants()
    }
}