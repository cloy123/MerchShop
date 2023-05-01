package com.monsieur.cloy.domain.models

class Product(
    var id: String,
    var typeName: String,
    var sizeName: String,
    var colorName: String,
    var colorValue: String,
    var showInCatalog: Boolean,
    var freeQuantity: Int,
    var price: Int,
    var discount: Int,
    var imageFileName: String
) {
}