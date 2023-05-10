package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.models.Order
import com.monsieur.cloy.domain.models.OrderItem
import com.monsieur.cloy.domain.repository.OrderItemRepository
import com.monsieur.cloy.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow

class GetOrderItemsUseCase(private val orderItemRepository: OrderItemRepository) {
    fun execute(): Flow<List<OrderItem>> {
        return orderItemRepository.getOrderItemsFlow()
    }
}