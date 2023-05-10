package com.monsieur.cloy.domain.repository

import com.monsieur.cloy.domain.models.CurrencyTransaction
import com.monsieur.cloy.domain.models.Notification
import com.monsieur.cloy.domain.models.common.UpdateCurrencyTransactionDataResult
import com.monsieur.cloy.domain.models.common.UpdateNotificationDataResult
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {

    fun getNotificationsFlow(): Flow<List<Notification>>

    suspend fun deleteAllData()

    suspend fun updateNotificationData(accessToken: String): UpdateNotificationDataResult

    suspend fun insertNotifications(notifications: List<Notification>)
}