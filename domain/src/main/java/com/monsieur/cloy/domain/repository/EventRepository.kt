package com.monsieur.cloy.domain.repository

import com.monsieur.cloy.domain.models.Event
import com.monsieur.cloy.domain.models.EventParticipant
import com.monsieur.cloy.domain.models.common.NoteVisitResult
import com.monsieur.cloy.domain.models.common.SignupEventResult
import com.monsieur.cloy.domain.models.common.UpdateEventDataResult
import kotlinx.coroutines.flow.Flow

interface EventRepository {

    suspend fun getEvents(): List<Event>

    fun getEventsFlow(): Flow<List<Event>>

    suspend fun deleteAllData()

    suspend fun updateEventData(accessToken: String): UpdateEventDataResult

    suspend fun insertEvents(events: List<Event>)

    suspend fun noteVisit(accessToken: String, eventId: String, participants: List<EventParticipant>): NoteVisitResult

    suspend fun signupEvent(accessToken: String, eventId: String, eventRoleId: String): SignupEventResult
}