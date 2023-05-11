package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.models.BasketItem
import com.monsieur.cloy.domain.models.Product
import com.monsieur.cloy.domain.repository.BasketItemRepository

class CreateBasketItemUseCase(private val basketItemRepository: BasketItemRepository) {
    suspend fun execute(product: Product){
        val basketItem = BasketItem(0, product, 1)
        basketItemRepository.insertBasketItem(basketItem)
    }
}