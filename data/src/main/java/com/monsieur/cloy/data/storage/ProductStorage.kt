package com.monsieur.cloy.data.storage

import com.monsieur.cloy.data.db.dao.ProductDao
import com.monsieur.cloy.data.storage.models.ProductEntity
import kotlinx.coroutines.flow.Flow

class ProductStorage(private val productDao: ProductDao) {

    suspend fun insertProducts(products: List<ProductEntity>){
        productDao.insertProducts(products)
    }

    fun getAllProducts(): Flow<List<ProductEntity>>{
        return productDao.getAllProducts()
    }

    suspend fun deleteAllProducts(){
        productDao.deleteAllProducts()
    }
}