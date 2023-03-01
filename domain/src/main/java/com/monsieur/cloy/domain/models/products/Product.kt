package com.monsieur.cloy.domain.models.products

class Product(
    val id: Int,
    val guid: String,
    val productType: ProductType,
    val productSize: ProductSize,
    val productColor: ProductColor,
    val freeQuantity: Int,
    val showInCatalog: Int,
    val price: Int,
    val discount: Int,
    val imageFileName: String
) {
}