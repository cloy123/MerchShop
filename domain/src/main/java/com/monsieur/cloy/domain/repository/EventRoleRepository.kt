package com.monsieur.cloy.domain.repository

import com.monsieur.cloy.domain.models.Event
import com.monsieur.cloy.domain.models.EventRole
import com.monsieur.cloy.domain.models.Product
import kotlinx.coroutines.flow.Flow

interface EventRoleRepository {

    suspend fun getEventRoles(): List<EventRole>

    fun getEventRolesFlow(): Flow<List<EventRole>>

    suspend fun deleteAllData()

    suspend fun insertEventRoles(eventRoles: List<EventRole>)

}