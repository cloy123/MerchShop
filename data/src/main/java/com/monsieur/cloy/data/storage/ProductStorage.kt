package com.monsieur.cloy.data.storage

import com.monsieur.cloy.data.db.dao.ProductDao
import com.monsieur.cloy.data.storage.models.ProductEntity
import kotlinx.coroutines.flow.Flow

class ProductStorage(private val productDao: ProductDao) {

    suspend fun insertProducts(products: List<ProductEntity>){
        productDao.insertProducts(products)
    }

    fun getProductsFlow(): Flow<List<ProductEntity>>{
        return productDao.getProductsFlow()
    }

    suspend fun getProducts(): List<ProductEntity>{
        return productDao.getProducts()
    }

    suspend fun deleteAllProducts(){
        productDao.deleteAllProducts()
    }
}