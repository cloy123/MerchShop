package com.monsieur.cloy.domain.models.common

import com.monsieur.cloy.domain.models.Product

class UpdateProductDataResult(
    val products: List<Product>?,
    val isSuccessful: Boolean,
    val code: Int
) {
}