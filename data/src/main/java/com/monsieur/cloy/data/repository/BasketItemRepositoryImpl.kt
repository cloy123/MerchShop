package com.monsieur.cloy.data.repository

import com.monsieur.cloy.data.api.MerchShopApi
import com.monsieur.cloy.data.api.models.requests.CreateOrderRequest
import com.monsieur.cloy.data.api.models.requests.OrderItemRequest
import com.monsieur.cloy.data.mappers.BasketItemMapper
import com.monsieur.cloy.data.storage.BasketItemStorage
import com.monsieur.cloy.domain.models.BasketItem
import com.monsieur.cloy.domain.models.common.CreateOrderResult
import com.monsieur.cloy.domain.repository.BasketItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BasketItemRepositoryImpl(private val merchShopApi: MerchShopApi, private val basketItemStorage: BasketItemStorage): BasketItemRepository {

    private val basketItemMapper = BasketItemMapper()

    override fun getBasketItemsFlow(): Flow<List<BasketItem>> {
        return basketItemStorage.getBasketItemsFlow().map { l -> l.map { basketItemMapper.toDomainModel(it) } }
    }

    override suspend fun getBasketItems(): List<BasketItem> {
        return basketItemStorage.getBasketItems().map { basketItemMapper.toDomainModel(it) }
    }

    override suspend fun insertBasketItem(basketItem: BasketItem) {
        basketItemStorage.insertBasketItem(basketItemMapper.toDataModel(basketItem))
    }

    override suspend fun deleteBasketItemById(id: Int) {
        basketItemStorage.deleteBasketItemById(id)
    }

    override suspend fun deleteAllBasketItems() {
        basketItemStorage.deleteAllBasketItems()
    }

    override suspend fun updateBasketItem(basketItem: BasketItem) {

    }

    override suspend fun createOrder(accessToken: String, basketItems: List<BasketItem>): CreateOrderResult {
        var isSuccessful: Boolean
        var isCreated: Boolean
        var code: Int
        var errorMessage: String
        try {
            val items = ArrayList<OrderItemRequest>()
            for(b in basketItems){
                items.add(OrderItemRequest(b.product.id, 1))
            }
            val response = merchShopApi.createOrder(accessToken, CreateOrderRequest(items))
            isSuccessful = response.isSuccessful
            code = response.code()
            if (response.isSuccessful && response.body() != null) {
                isCreated = response.body()!!.isCreated
                errorMessage = response.body()!!.errorMessage
            } else {
                isCreated = false
                errorMessage = ""
            }
        } catch (e: Exception) {
            code = -1
            isSuccessful = false
            isCreated = false
            errorMessage = ""
        }
        return CreateOrderResult(isSuccessful, isCreated, errorMessage, code)
    }
}