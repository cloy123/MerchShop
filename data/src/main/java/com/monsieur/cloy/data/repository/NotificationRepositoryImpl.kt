package com.monsieur.cloy.data.repository

import com.monsieur.cloy.data.api.MerchShopApi
import com.monsieur.cloy.data.mappers.NotificationMapper
import com.monsieur.cloy.data.storage.NotificationStorage
import com.monsieur.cloy.domain.models.Notification
import com.monsieur.cloy.domain.models.common.UpdateNotificationDataResult
import com.monsieur.cloy.domain.repository.NotificationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NotificationRepositoryImpl(
    private val notificationStorage: NotificationStorage,
    private val merchShopApi: MerchShopApi): NotificationRepository {

    private val notificationMapper = NotificationMapper()

    override fun getNotificationsFlow(): Flow<List<Notification>> {
        return notificationStorage.getNotificationsFlow().map { l -> l.map { notificationMapper.toDomainModel(it) } }
    }

    override suspend fun deleteAllData() {
        notificationStorage.deleteAllNotifications()
    }

    override suspend fun updateNotificationData(accessToken: String): UpdateNotificationDataResult {
        var isSuccessful: Boolean
        var notifications: List<Notification>?
        var code: Int
        try {
            val response = merchShopApi.getNotificationsInfo(accessToken)
            isSuccessful = response.isSuccessful
            code = response.code()
            if (response.isSuccessful && response.body() != null) {
                notifications = response.body()!!.notifications.map { notificationMapper.notificationDtoToNotification(it) }
            } else {
                notifications = null
            }
        } catch (e: Exception) {
            code = -1
            isSuccessful = false
            notifications = null
        }
        return UpdateNotificationDataResult(notifications, isSuccessful, code)
    }

    override suspend fun insertNotifications(notifications: List<Notification>) {
        notificationStorage.insertNotifications(notifications.map { notificationMapper.toDataModel(it) })
    }
}