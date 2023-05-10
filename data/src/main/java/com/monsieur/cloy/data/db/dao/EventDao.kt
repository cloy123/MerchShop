package com.monsieur.cloy.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.monsieur.cloy.data.storage.models.EventEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @Insert
    suspend fun insertEvents(events: List<EventEntity>)

    @Query("SELECT * FROM events")
    fun getEventsFlow(): Flow<List<EventEntity>>

    @Query("SELECT * FROM events")
    suspend fun getEvents(): List<EventEntity>

    @Query("DELETE FROM events")
    suspend fun deleteAllEvents()
}