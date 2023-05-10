package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.models.Notification
import com.monsieur.cloy.domain.models.Product
import com.monsieur.cloy.domain.repository.NotificationRepository
import com.monsieur.cloy.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetNotificationsUseCase(private val notificationRepository: NotificationRepository) {
    fun execute(): Flow<List<Notification>> {
        return notificationRepository.getNotificationsFlow()
    }
}