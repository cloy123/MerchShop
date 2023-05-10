package com.monsieur.cloy.domain.repository

import com.monsieur.cloy.domain.models.Event
import com.monsieur.cloy.domain.models.EventParticipant
import kotlinx.coroutines.flow.Flow

interface EventParticipantRepository {

    suspend fun getEventParticipants(): List<EventParticipant>

    fun getEventParticipantsFlow(): Flow<List<EventParticipant>>

    suspend fun deleteAllData()

    suspend fun insertEventParticipants(eventParticipants: List<EventParticipant>)
}