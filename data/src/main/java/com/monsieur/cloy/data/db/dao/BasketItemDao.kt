package com.monsieur.cloy.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.monsieur.cloy.data.storage.models.BasketItemEntity
import com.monsieur.cloy.data.storage.models.BasketItemWithProduct
import kotlinx.coroutines.flow.Flow

@Dao
interface BasketItemDao {
    @Update
    suspend fun updateBasketItem(basketItem: BasketItemEntity)

    @Insert
    suspend fun insertBasketItem(basketItem: BasketItemEntity)

    @Query("DELETE FROM basketItems WHERE basketItemId = :id")
    suspend fun deleteBasketItemById(id: Int)

    @Query("SELECT * FROM basketItems  INNER JOIN products on products.id = basketItems.productId")
    fun getAllBasketItems(): Flow<List<BasketItemWithProduct>>
}