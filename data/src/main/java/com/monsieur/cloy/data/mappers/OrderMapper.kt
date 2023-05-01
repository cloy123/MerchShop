package com.monsieur.cloy.data.mappers

import com.monsieur.cloy.data.storage.models.NotificationEntity
import com.monsieur.cloy.data.storage.models.OrderEntity
import com.monsieur.cloy.domain.models.Notification
import com.monsieur.cloy.domain.models.Order

class OrderMapper {

    fun toDomainModel(orderEntity: OrderEntity): Order {
        return Order(
            orderEntity.id,
            orderEntity.dateCreation,
            orderEntity.dateCompletion,
            orderEntity.statusId,
            orderEntity.orderSum
        )
    }

    fun toDataModel(order: Order): OrderEntity {
        val entity = OrderEntity()
        entity.id = order.id
        entity.dateCreation = order.dateCreation
        entity.dateCompletion = order.dateCompletion
        entity.statusId = order.statusId
        entity.orderSum = order.orderSum
        return entity
    }
}