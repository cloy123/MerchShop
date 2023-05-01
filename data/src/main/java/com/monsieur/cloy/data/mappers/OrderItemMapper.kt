package com.monsieur.cloy.data.mappers

import com.monsieur.cloy.data.storage.models.NotificationEntity
import com.monsieur.cloy.data.storage.models.OrderEntity
import com.monsieur.cloy.data.storage.models.OrderItemEntity
import com.monsieur.cloy.data.storage.models.OrderItemWithProduct
import com.monsieur.cloy.domain.models.Notification
import com.monsieur.cloy.domain.models.OrderItem
import com.monsieur.cloy.domain.models.Product

class OrderItemMapper {
    fun toDomainModel(orderItemWithProduct: OrderItemWithProduct): OrderItem {
        val product = Product(
            orderItemWithProduct.productId,
            orderItemWithProduct.typeName,
            orderItemWithProduct.sizeName,
            orderItemWithProduct.colorName,
            orderItemWithProduct.colorValue,
            orderItemWithProduct.showInCatalog,
            orderItemWithProduct.freeQuantity,
            orderItemWithProduct.price,
            orderItemWithProduct.discount,
            orderItemWithProduct.imageFileName
        )

        return OrderItem(
            orderItemWithProduct.id,
            orderItemWithProduct.orderId,
            orderItemWithProduct.quantity,
            orderItemWithProduct.itemPrice,
            product
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
}