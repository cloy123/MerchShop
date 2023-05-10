package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.models.BasketItem
import com.monsieur.cloy.domain.repository.BasketItemRepository
import kotlinx.coroutines.flow.Flow

class GetBasketItemsUseCase (private val basketItemRepository: BasketItemRepository) {
    fun execute(): Flow<List<BasketItem>> {
        return basketItemRepository.getBasketItemsFlow()
    }
}