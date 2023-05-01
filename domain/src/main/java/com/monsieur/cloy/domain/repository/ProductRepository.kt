package com.monsieur.cloy.domain.repository

import com.monsieur.cloy.domain.models.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun insertProducts(products: List<Product>)

    fun getAllProducts(): Flow<List<Product>>

    suspend fun deleteAllProducts()

    suspend fun updateProductData()
}