package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.models.Product
import com.monsieur.cloy.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetProductsUseCase(private val productRepository: ProductRepository) {
    fun execute(): Flow<List<Product>> {
        return productRepository.getProductsFlow()
    }
}