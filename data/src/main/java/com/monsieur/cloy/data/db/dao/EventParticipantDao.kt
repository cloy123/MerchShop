package com.monsieur.cloy.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.monsieur.cloy.data.storage.models.EventParticipantEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EventParticipantDao {
    @Insert
    suspend fun insertEventParticipants(eventParticipants: List<EventParticipantEntity>)

    @Query("SELECT * FROM eventParticipants")
    fun getAllEventParticipants(): Flow<List<EventParticipantEntity>>

    @Query("DELETE FROM eventParticipants")
    fun deleteAllEventParticipants()
}