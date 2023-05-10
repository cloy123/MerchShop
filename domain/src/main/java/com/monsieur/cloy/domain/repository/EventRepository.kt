package com.monsieur.cloy.domain.repository

import com.monsieur.cloy.domain.models.Event
import com.monsieur.cloy.domain.models.Product
import com.monsieur.cloy.domain.models.common.UpdateEventDataResult
import kotlinx.coroutines.flow.Flow

interface EventRepository {

    suspend fun getEvents(): List<Event>

    fun getEventsFlow(): Flow<List<Event>>

    suspend fun deleteAllData()

    suspend fun updateEventData(accessToken: String): UpdateEventDataResult

    suspend fun insertEvents(events: List<Event>)

}