package com.monsieur.cloy.domain.repository

import com.monsieur.cloy.domain.models.EventResponsible
import kotlinx.coroutines.flow.Flow

interface EventResponsibleRepository {

    suspend fun getEventResponsibles(): List<EventResponsible>

    fun getEventResponsiblesFlow(): Flow<List<EventResponsible>>

    suspend fun deleteAllData()

    suspend fun insertEventResponsibles(eventResponsibles: List<EventResponsible>)
}