package com.monsieur.cloy.data.mappers

import com.monsieur.cloy.data.storage.models.EventRoleEntity
import com.monsieur.cloy.data.storage.models.NotificationEntity
import com.monsieur.cloy.domain.models.EventRole
import com.monsieur.cloy.domain.models.Notification

class NotificationMapper {

    fun toDomainModel(notificationEntity: NotificationEntity): Notification {
        return Notification(
            notificationEntity.id,
            notificationEntity.message,
            notificationEntity.isSend,
            notificationEntity.dateTime
        )
    }

    fun toDataModel(notification: Notification): NotificationEntity {
        val entity = NotificationEntity()
        entity.id = notification.id
        entity.message = notification.message
        entity.isSend = notification.isSend
        entity.dateTime = notification.dateTime
        return entity
    }
}