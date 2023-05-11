package com.monsieur.cloy.data.repository

import com.monsieur.cloy.data.api.MerchShopApi
import com.monsieur.cloy.data.mappers.ProductMapper
import com.monsieur.cloy.data.storage.ProductStorage
import com.monsieur.cloy.domain.models.Product
import com.monsieur.cloy.domain.models.common.UpdateProductDataResult
import com.monsieur.cloy.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductRepositoryImpl(
    private val merchShopApi: MerchShopApi,
    private val productStorage: ProductStorage
) : ProductRepository {

    private val productMapper = ProductMapper()

    override suspend fun insertProducts(products: List<Product>) {
        productStorage.insertProducts(products.map { productMapper.toDataModel(it) })
    }

    override fun getProductsFlow(): Flow<List<Product>> {
        return productStorage.getProductsFlow().map { list ->
            list.map {
                productMapper.toDomainModel(it)
            }
        }
    }

    override suspend fun deleteAllProducts() {
        productStorage.deleteAllProducts()
    }

    override suspend fun updateProductData(accessToken: String): UpdateProductDataResult {
        var isSuccessful: Boolean
        var products: List<Product>?
        var code: Int
        try {
            val response = merchShopApi.getCatalogInfo(accessToken)
            isSuccessful = response.isSuccessful
            code = response.code()
            if (response.isSuccessful && response.body() != null) {
                products = response.body()!!.products.map { productMapper.productDtoToProduct(it) }
            } else {
                products = null
            }
        } catch (e: Exception) {
            code = -1
            isSuccessful = false
            products = null
        }
        return UpdateProductDataResult(products, isSuccessful, code)
    }

    override suspend fun getProducts(): List<Product> {
        return productStorage.getProducts().map { productMapper.toDomainModel(it) }
    }
}