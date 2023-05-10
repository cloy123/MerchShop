package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.models.Order
import com.monsieur.cloy.domain.repository.OrderRepository

import kotlinx.coroutines.flow.Flow

class GetOrdersUseCase(private val orderRepository: OrderRepository) {
    fun execute(): Flow<List<Order>> {
        return orderRepository.getOrdersFlow()
    }
}