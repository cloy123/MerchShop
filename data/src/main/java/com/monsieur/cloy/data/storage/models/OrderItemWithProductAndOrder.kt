package com.monsieur.cloy.data.storage.models

import androidx.room.ColumnInfo
import java.time.LocalDateTime

class OrderItemWithProductAndOrder {
    @ColumnInfo(name = "id")
    var id: String = ""

    @ColumnInfo(name = "orderId")
    var orderId: String = ""

    @ColumnInfo(name = "productId")
    var productId: String = ""

    @ColumnInfo(name = "quantity")
    var quantity: Int = 0

    @ColumnInfo(name = "itemPrice")
    var itemPrice: Int = 0

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

    @ColumnInfo(name = "dateCreation")
    var dateCreation: LocalDateTime = LocalDateTime.now()

    @ColumnInfo(name = "dateCompletion")
    var dateCompletion: LocalDateTime? = LocalDateTime.now()

    @ColumnInfo(name = "statusId")
    var statusId: Int = 0

    @ColumnInfo(name = "orderSum")
    var orderSum: Int = 0
}