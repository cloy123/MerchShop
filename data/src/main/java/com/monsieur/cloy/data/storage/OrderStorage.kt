package com.monsieur.cloy.data.storage

import com.monsieur.cloy.data.db.dao.OrderDao
import com.monsieur.cloy.data.storage.models.OrderEntity
import kotlinx.coroutines.flow.Flow

class OrderStorage(private val orderDao: OrderDao) {

    suspend fun insertOrders(orders: List<OrderEntity>){
        orderDao.insertOrders(orders)
    }

    fun getAllOrders(): Flow<List<OrderEntity>>{
        return orderDao.getAllOrders()
    }

    fun deleteAllOrders(){
        orderDao.deleteAllOrders()
    }
}