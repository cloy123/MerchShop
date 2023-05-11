package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.models.Notification
import com.monsieur.cloy.domain.repository.NotificationRepository
import kotlinx.coroutines.flow.Flow

class GetNotificationsUseCase(private val notificationRepository: NotificationRepository) {
    fun execute(): Flow<List<Notification>> {
        return notificationRepository.getNotificationsFlow()
    }
}