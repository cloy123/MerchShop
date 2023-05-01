package com.monsieur.cloy.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.monsieur.cloy.data.storage.models.EventRoleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EventRoleDao {
    @Insert
    suspend fun insertEventRoles(eventRoles: List<EventRoleEntity>)

    @Query("SELECT * FROM eventRoles")
    fun getAllEventRoles(): Flow<List<EventRoleEntity>>

    @Query("DELETE FROM eventRoles")
    suspend fun deleteAllEventRoles()
}