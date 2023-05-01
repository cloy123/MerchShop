package com.monsieur.cloy.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.monsieur.cloy.data.storage.models.OrderItemEntity
import com.monsieur.cloy.data.storage.models.OrderItemWithProduct
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderItemDao {
    @Insert
    suspend fun insertOrderItems(orderItems: List<OrderItemEntity>)

    @Query("SELECT * FROM orderItems INNER JOIN products on orderItems.orderId = products.id")
    fun getAllOrderItems(): Flow<List<OrderItemWithProduct>>

    @Query("DELETE FROM orderItems")
    suspend fun deleteAllOrderItems()
}