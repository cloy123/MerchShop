package com.monsieur.cloy.data.storage

import com.monsieur.cloy.data.db.dao.OrderItemDao
import com.monsieur.cloy.data.storage.models.OrderItemEntity
import com.monsieur.cloy.data.storage.models.OrderItemWithProductAndOrder
import kotlinx.coroutines.flow.Flow

class OrderItemStorage(private val orderItemDao: OrderItemDao) {

    suspend fun insertOrderItems(orderItems: List<OrderItemEntity>){
        orderItemDao.insertOrderItems(orderItems)
    }

    fun getOrderItemsFlow(): Flow<List<OrderItemWithProductAndOrder>>{
        return orderItemDao.getOrderItemsFlow()
    }

    suspend fun getOrderItems(): List<OrderItemWithProductAndOrder>{
        return orderItemDao.getOrderItems()
    }

    suspend fun deleteAllOrderItems(){
        orderItemDao.deleteAllOrderItems()
    }
}