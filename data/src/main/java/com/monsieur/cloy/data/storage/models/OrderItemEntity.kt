package com.monsieur.cloy.data.storage.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "orderItems")
class OrderItemEntity {
    @ColumnInfo(name = "id")
    @NonNull
    @PrimaryKey(autoGenerate = false)
    var id: String = ""

    @ColumnInfo(name = "orderId")
    @NonNull
    var orderId: String = ""

    @ColumnInfo(name = "productId")
    @NonNull
    var productId: String = ""

    @ColumnInfo(name = "quantity")
    @NonNull
    var quantity: Int = 0

    @ColumnInfo(name = "itemPrice")
    @NonNull
    var itemPrice: Int = 0
}