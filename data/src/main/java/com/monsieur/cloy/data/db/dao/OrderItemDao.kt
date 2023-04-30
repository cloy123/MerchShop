package com.monsieur.cloy.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.monsieur.cloy.data.storage.models.OrderItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderItemDao {
    @Insert
    suspend fun insertOrderItems(orderItems: List<OrderItemEntity>)

    @Query("SELECT * FROM orderItems")
    fun getAllOrderItems(): Flow<List<OrderItemEntity>>

    @Query("DELETE FROM orderItems")
    fun deleteAllOrderItems()
}