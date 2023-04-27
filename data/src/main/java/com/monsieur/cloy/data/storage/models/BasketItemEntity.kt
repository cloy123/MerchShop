package com.monsieur.cloy.data.storage.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "basketItems")
class BasketItemEntity {
    @ColumnInfo(name = "basketItem_id")
    @NonNull
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "basketItemQuantity")
    @NonNull
    var quantity: Int = 0

    @ColumnInfo(name = "productId")
    @NonNull
    var productId: String = ""
}