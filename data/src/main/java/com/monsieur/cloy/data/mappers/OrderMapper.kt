package com.monsieur.cloy.data.mappers

import com.monsieur.cloy.data.api.models.NotificationDto
import com.monsieur.cloy.data.api.models.OrderDto
import com.monsieur.cloy.data.storage.models.NotificationEntity
import com.monsieur.cloy.data.storage.models.OrderEntity
import com.monsieur.cloy.domain.models.Notification
import com.monsieur.cloy.domain.models.Order
import com.monsieur.cloy.domain.models.OrderItem
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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

    fun orderDtoToOrder(orderDto: OrderDto): Order {
        val dateCompletion: LocalDateTime? = if(orderDto.dateCompletion == null){
            null
        }else{
            LocalDateTime.parse(orderDto.dateCompletion, DateTimeFormatter.ISO_DATE_TIME)
        }
        return Order(
            orderDto.id,
            LocalDateTime.parse(orderDto.dateCreation, DateTimeFormatter.ISO_DATE_TIME),
            dateCompletion,
            orderDto.statusId,
            orderDto.orderSum
        )
    }
}