package com.monsieur.cloy.data.storage

import com.monsieur.cloy.data.db.dao.BasketItemDao
import com.monsieur.cloy.data.storage.models.BasketItemEntity
import com.monsieur.cloy.data.storage.models.BasketItemWithProduct
import kotlinx.coroutines.flow.Flow

class BasketItemStorage(private val basketItemDao: BasketItemDao) {

    fun getBasketItemsFlow(): Flow<List<BasketItemWithProduct>> {
        return basketItemDao.getBasketItemsFlow()
    }

    suspend fun getBasketItems(): List<BasketItemWithProduct> {
        return basketItemDao.getBasketItems()
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

    suspend fun deleteAllBasketItems(){
        basketItemDao.deleteAllBasketItems()
    }
}