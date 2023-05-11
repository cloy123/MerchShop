package com.monsieur.cloy.data.mappers

import com.monsieur.cloy.data.api.models.OrderItemDto
import com.monsieur.cloy.data.storage.models.OrderItemEntity
import com.monsieur.cloy.data.storage.models.OrderItemWithProductAndOrder
import com.monsieur.cloy.domain.models.Order
import com.monsieur.cloy.domain.models.OrderItem
import com.monsieur.cloy.domain.models.Product

class OrderItemMapper {
    fun toDomainModel(orderItemWithProductAndOrder: OrderItemWithProductAndOrder): OrderItem {
        val product = Product(
            orderItemWithProductAndOrder.productId,
            orderItemWithProductAndOrder.typeName,
            orderItemWithProductAndOrder.sizeName,
            orderItemWithProductAndOrder.colorName,
            orderItemWithProductAndOrder.colorValue,
            orderItemWithProductAndOrder.showInCatalog,
            orderItemWithProductAndOrder.freeQuantity,
            orderItemWithProductAndOrder.price,
            orderItemWithProductAndOrder.discount,
            orderItemWithProductAndOrder.imageFileName
        )

        val order = Order(
            orderItemWithProductAndOrder.orderId,
            orderItemWithProductAndOrder.dateCreation,
            orderItemWithProductAndOrder.dateCompletion,
            orderItemWithProductAndOrder.statusId,
            orderItemWithProductAndOrder.orderSum
        )

        return OrderItem(
            orderItemWithProductAndOrder.id,
            orderItemWithProductAndOrder.orderId,
            orderItemWithProductAndOrder.quantity,
            orderItemWithProductAndOrder.itemPrice,
            product,
            order
        )
    }

    fun toDataModel(orderItem: OrderItem): OrderItemEntity {
        val entity = OrderItemEntity()
        entity.id = orderItem.id
        entity.orderId = orderItem.orderId
        entity.quantity = orderItem.quantity
        entity.itemPrice = orderItem.itemPrice
        entity.productId = orderItem.product.id
        return entity
    }

    fun orderItemDtoToOrderItem(orderItemDto: OrderItemDto, product: Product, order: Order): OrderItem {
        return OrderItem(
            orderItemDto.id,
            orderItemDto.orderId,
            orderItemDto.quantity,
            orderItemDto.price,
            product,
            order
        )
    }
}