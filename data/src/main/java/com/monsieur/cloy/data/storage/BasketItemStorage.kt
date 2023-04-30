package com.monsieur.cloy.data.storage

import com.monsieur.cloy.data.db.dao.BasketItemDao
import com.monsieur.cloy.data.storage.models.BasketItemEntity
import com.monsieur.cloy.data.storage.models.BasketItemWithProduct
import kotlinx.coroutines.flow.Flow

class BasketItemStorage(private val basketItemDao: BasketItemDao) {
    fun getAllBasketItems(): Flow<List<BasketItemWithProduct>> {
        return basketItemDao.getAllBasketItems()
    }

    suspend fun insertBasketItem(basketItem: BasketItemEntity) {
        basketItemDao.insertBasketItem(basketItem)
    }

    suspend fun deleteBasketItemById(id: Int) {
        basketItemDao.deleteBasketItemById(id)
    }

    suspend fun updateBasketItem(basketItem: BasketItemEntity) {
        basketItemDao.updateBasketItem(basketItem)
    }
}