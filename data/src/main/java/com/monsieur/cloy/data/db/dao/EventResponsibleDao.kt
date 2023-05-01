package com.monsieur.cloy.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.monsieur.cloy.data.storage.models.EventResponsibleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EventResponsibleDao {
    @Insert
    suspend fun insertEventResponsibles(eventResponsibles: List<EventResponsibleEntity>)

    @Query("SELECT * FROM eventResponsibles")
    fun getAllEventResponsibles(): Flow<List<EventResponsibleEntity>>

    @Query("DELETE FROM eventResponsibles")
    suspend fun deleteAllEventResponsibles()
}