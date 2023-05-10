package com.monsieur.cloy.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.monsieur.cloy.data.storage.models.OrderItemEntity
import com.monsieur.cloy.data.storage.models.OrderItemWithProductAndOrder
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderItemDao {
    @Insert
    suspend fun insertOrderItems(orderItems: List<OrderItemEntity>)

    @Query("SELECT * FROM orderItems INNER JOIN products on orderItems.productId = products.id INNER JOIN orders on orderItems.orderId = orders.id")
    fun getOrderItemsFlow(): Flow<List<OrderItemWithProductAndOrder>>

    @Query("SELECT * FROM orderItems INNER JOIN products on orderItems.productId = products.id INNER JOIN orders on orderItems.orderId = orders.id")
    suspend fun getOrderItems(): List<OrderItemWithProductAndOrder>

    @Query("DELETE FROM orderItems")
    suspend fun deleteAllOrderItems()
}