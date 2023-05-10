package com.monsieur.cloy.domain.repository

import com.monsieur.cloy.domain.models.Notification
import com.monsieur.cloy.domain.models.Order
import com.monsieur.cloy.domain.models.common.UpdateNotificationDataResult
import com.monsieur.cloy.domain.models.common.UpdateOrderDataResult
import kotlinx.coroutines.flow.Flow

interface OrderRepository {

    fun getOrdersFlow(): Flow<List<Order>>

    suspend fun deleteAllData()

    suspend fun updateOrderData(accessToken: String): UpdateOrderDataResult

    suspend fun insertOrders(orders: List<Order>)
}