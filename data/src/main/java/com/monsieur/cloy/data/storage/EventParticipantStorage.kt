package com.monsieur.cloy.data.storage

import com.monsieur.cloy.data.db.dao.EventParticipantDao
import com.monsieur.cloy.data.storage.models.EventParticipantEntity
import com.monsieur.cloy.data.storage.models.EventParticipantWithRole
import kotlinx.coroutines.flow.Flow

class EventParticipantStorage(private val eventParticipantDao: EventParticipantDao) {
    suspend fun insertEventParticipants(eventParticipants: List<EventParticipantEntity>){
        eventParticipantDao.insertEventParticipants(eventParticipants)
    }

    fun getAllEventParticipantsFlow(): Flow<List<EventParticipantWithRole>>{
        return eventParticipantDao.getEventParticipantsFlow()
    }

    suspend fun getEventParticipants(): List<EventParticipantWithRole>{
        return eventParticipantDao.getEventParticipants()
    }

    suspend fun deleteAllEventParticipants(){
        eventParticipantDao.deleteAllEventParticipants()
    }
}