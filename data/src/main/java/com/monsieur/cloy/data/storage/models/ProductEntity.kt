package com.monsieur.cloy.data.storage.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
class ProductEntity {

    @ColumnInfo(name = "id")
    @NonNull
    @PrimaryKey(autoGenerate = false)
    var id: String = ""

    @ColumnInfo(name = "typeName")
    @NonNull
    var typeName: String = ""

    @ColumnInfo(name = "sizeName")
    @NonNull
    var sizeName: String = ""

    @ColumnInfo(name = "colorName")
    @NonNull
    var colorName: String = ""

    @ColumnInfo(name = "colorValue")
    @NonNull
    var colorValue: String = ""

    @ColumnInfo(name = "showInCatalog")
    @NonNull
    var showInCatalog: Boolean = false

    @ColumnInfo(name = "freeQuantity")
    @NonNull
    var freeQuantity: Int = 0

    @ColumnInfo(name = "price")
    @NonNull
    var price: Int = 0

    @ColumnInfo(name = "discount")
    @NonNull
    var discount: Int = 0

    @ColumnInfo(name = "imageFileName")
    @NonNull
    var imageFileName: String = ""
}