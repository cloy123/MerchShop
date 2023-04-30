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
    fun getAllEvents(): Flow<List<EventEntity>>

    @Query("DELETE FROM events")
    fun deleteAllEvents()
}