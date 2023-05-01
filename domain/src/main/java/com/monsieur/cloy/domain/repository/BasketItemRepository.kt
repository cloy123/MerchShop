package com.monsieur.cloy.domain.repository

import com.monsieur.cloy.domain.models.BasketItem
import kotlinx.coroutines.flow.Flow

interface BasketItemRepository {

    fun getAllBasketItems(userId: Int): Flow<List<BasketItem>>

    suspend fun insertBasketItem(basketItem: BasketItem)

    suspend fun deleteBasketItemById(id: Int)

    suspend fun updateBasketItem(basketItem: BasketItem)
}