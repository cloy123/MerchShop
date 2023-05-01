package com.monsieur.cloy.data.storage

import com.monsieur.cloy.data.db.dao.OrderItemDao
import com.monsieur.cloy.data.storage.models.OrderItemEntity
import com.monsieur.cloy.data.storage.models.OrderItemWithProduct
import kotlinx.coroutines.flow.Flow

class OrderItemStorage(private val orderItemDao: OrderItemDao) {

    suspend fun insertOrderItems(orderItems: List<OrderItemEntity>){
        orderItemDao.insertOrderItems(orderItems)
    }

    fun getAllOrderItems(): Flow<List<OrderItemWithProduct>>{
        return orderItemDao.getAllOrderItems()
    }

    suspend fun deleteAllOrderItems(){
        orderItemDao.deleteAllOrderItems()
    }
}