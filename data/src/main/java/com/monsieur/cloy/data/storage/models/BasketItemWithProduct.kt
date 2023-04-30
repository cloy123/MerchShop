package com.monsieur.cloy.data.storage.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

class BasketItemWithProduct {
    @ColumnInfo(name = "basketItemId")
    var id: Int = 0

    @ColumnInfo(name = "basketItemQuantity")
    var quantity: Int = 0

    @ColumnInfo(name = "productId")
    var productId: String = ""

    @ColumnInfo(name = "typeName")
    var typeName: String = ""

    @ColumnInfo(name = "sizeName")
    var sizeName: String = ""

    @ColumnInfo(name = "colorName")
    var colorName: String = ""

    @ColumnInfo(name = "colorValue")
    var colorValue: String = ""

    @ColumnInfo(name = "showInCatalog")
    var showInCatalog: Boolean = false

    @ColumnInfo(name = "freeQuantity")
    var freeQuantity: Int = 0

    @ColumnInfo(name = "price")
    var price: Int = 0

    @ColumnInfo(name = "discount")
    var discount: Int = 0

    @ColumnInfo(name = "imageFileName")
    var imageFileName: String = ""
}