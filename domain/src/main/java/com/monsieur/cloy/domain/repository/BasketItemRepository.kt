package com.monsieur.cloy.domain.repository

import com.monsieur.cloy.domain.models.BasketItem
import com.monsieur.cloy.domain.models.common.CreateOrderResult
import kotlinx.coroutines.flow.Flow

interface BasketItemRepository {

    fun getBasketItemsFlow(): Flow<List<BasketItem>>

    suspend fun getBasketItems(): List<BasketItem>

    suspend fun insertBasketItem(basketItem: BasketItem)

    suspend fun deleteBasketItemById(id: Int)

    suspend fun deleteAllBasketItems()

    suspend fun updateBasketItem(basketItem: BasketItem)

    suspend fun createOrder(accessToken: String, basketItems: List<BasketItem>): CreateOrderResult
}