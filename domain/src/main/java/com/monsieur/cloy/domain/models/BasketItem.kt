package com.monsieur.cloy.domain.models

import com.monsieur.cloy.domain.models.products.Product

class BasketItem(
    val id: Int,
    val productGuid: String,
    val product: Product,
    val quantity: Int
) {
}