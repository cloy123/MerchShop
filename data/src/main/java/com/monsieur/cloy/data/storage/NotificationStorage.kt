package com.monsieur.cloy.data.storage

import com.monsieur.cloy.data.db.dao.NotificationDao
import com.monsieur.cloy.data.storage.models.NotificationEntity
import kotlinx.coroutines.flow.Flow

class NotificationStorage(private val notificationDao: NotificationDao) {

    suspend fun insertNotifications(notifications: List<NotificationEntity>){
        notificationDao.insertNotifications(notifications)
    }

    fun getAllNotifications(): Flow<List<NotificationEntity>>{
        return notificationDao.getAllNotifications()
    }

    fun deleteAllNotifications(){
        notificationDao.deleteAllNotifications()
    }
}