package com.monsieur.cloy.data.storage

import com.monsieur.cloy.data.db.dao.EventRoleDao
import com.monsieur.cloy.data.storage.models.EventRoleEntity
import kotlinx.coroutines.flow.Flow

class EventRoleStorage(private val eventRoleDao: EventRoleDao) {

    suspend fun insertEventRoles(eventRoles: List<EventRoleEntity>){
        eventRoleDao.insertEventRoles(eventRoles)
    }

    fun getAllEventRoles(): Flow<List<EventRoleEntity>>{
        return eventRoleDao.getAllEventRoles()
    }

    suspend fun deleteAllEventRoles(){
        eventRoleDao.deleteAllEventRoles()
    }
}