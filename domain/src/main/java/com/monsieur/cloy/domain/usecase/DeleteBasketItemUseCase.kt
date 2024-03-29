package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.repository.BasketItemRepository

class DeleteBasketItemUseCase (private val basketItemRepository: BasketItemRepository) {
    suspend fun execute(id: Int){
        basketItemRepository.deleteBasketItemById(id)
    }
}