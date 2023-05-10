package com.monsieur.cloy.domain.repository

import com.monsieur.cloy.domain.models.Order
import com.monsieur.cloy.domain.models.OrderItem
import com.monsieur.cloy.domain.models.common.UpdateOrderDataResult
import kotlinx.coroutines.flow.Flow

interface OrderItemRepository {

    fun getOrderItemsFlow(): Flow<List<OrderItem>>

    suspend fun deleteAllData()

    suspend fun insertOrderItems(orderItems: List<OrderItem>)
}