package com.monsieur.cloy.data.storage

import com.monsieur.cloy.data.db.dao.NotificationDao
import com.monsieur.cloy.data.storage.models.NotificationEntity
import kotlinx.coroutines.flow.Flow

class NotificationStorage(private val notificationDao: NotificationDao) {

    suspend fun insertNotifications(notifications: List<NotificationEntity>){
        notificationDao.insertNotifications(notifications)
    }

    fun getNotificationsFlow(): Flow<List<NotificationEntity>>{
        return notificationDao.getNotificationsFlow()
    }

    suspend fun deleteAllNotifications(){
        notificationDao.deleteAllNotifications()
    }
}