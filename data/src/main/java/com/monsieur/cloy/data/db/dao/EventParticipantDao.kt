package com.monsieur.cloy.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.monsieur.cloy.data.storage.models.EventParticipantEntity
import com.monsieur.cloy.data.storage.models.EventParticipantWithRole
import kotlinx.coroutines.flow.Flow

@Dao
interface EventParticipantDao {
    @Insert
    suspend fun insertEventParticipants(eventParticipants: List<EventParticipantEntity>)

    @Query("SELECT * FROM eventParticipants INNER JOIN eventRoles on eventParticipants.eventRoleId = eventRoles.id")
    fun getEventParticipantsFlow(): Flow<List<EventParticipantWithRole>>

    @Query("SELECT * FROM eventParticipants INNER JOIN eventRoles on eventParticipants.eventRoleId = eventRoles.id")
    suspend fun getEventParticipants(): List<EventParticipantWithRole>

    @Query("DELETE FROM eventParticipants")
    suspend fun deleteAllEventParticipants()
}