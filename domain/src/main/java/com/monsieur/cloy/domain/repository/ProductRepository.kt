package com.monsieur.cloy.domain.repository

import com.monsieur.cloy.domain.models.Product
import com.monsieur.cloy.domain.models.common.UpdateProductDataResult
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun insertProducts(products: List<Product>)

    fun getProductsFlow(): Flow<List<Product>>

    suspend fun deleteAllProducts()

    suspend fun updateProductData(accessToken: String): UpdateProductDataResult

    suspend fun getProducts(): List<Product>
}