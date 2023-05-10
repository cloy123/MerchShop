package com.monsieur.cloy.data.repository

import com.monsieur.cloy.data.mappers.OrderItemMapper
import com.monsieur.cloy.data.storage.OrderItemStorage
import com.monsieur.cloy.domain.models.OrderItem
import com.monsieur.cloy.domain.repository.OrderItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OrderItemRepositoryImpl(private val orderItemStorage: OrderItemStorage): OrderItemRepository {

    private val orderItemMapper = OrderItemMapper()

    override fun getOrderItemsFlow(): Flow<List<OrderItem>> {
        return orderItemStorage.getOrderItemsFlow().map { l -> l.map { orderItemMapper.toDomainModel(it) } }
    }

    override suspend fun deleteAllData() {
        orderItemStorage.deleteAllOrderItems()
    }

    override suspend fun insertOrderItems(orderItems: List<OrderItem>) {
        orderItemStorage.insertOrderItems(orderItems.map { orderItemMapper.toDataModel(it) })
    }
}