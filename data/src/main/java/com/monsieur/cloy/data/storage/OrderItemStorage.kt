package com.monsieur.cloy.data.storage

import com.monsieur.cloy.data.db.dao.OrderItemDao
import com.monsieur.cloy.data.storage.models.OrderItemEntity
import kotlinx.coroutines.flow.Flow

class OrderItemStorage(private val orderItemDao: OrderItemDao) {

    suspend fun insertOrderItems(orderItems: List<OrderItemEntity>){
        orderItemDao.insertOrderItems(orderItems)
    }

    fun getAllOrderItems(): Flow<List<OrderItemEntity>>{
        return orderItemDao.getAllOrderItems()
    }

    fun deleteAllOrderItems(){
        orderItemDao.deleteAllOrderItems()
    }
}