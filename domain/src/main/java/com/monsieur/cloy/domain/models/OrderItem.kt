package com.monsieur.cloy.domain.models

class OrderItem(
    var id: String,
    var orderId: String,
    var quantity: Int,
    var itemPrice: Int,
    var product: Product
) {
}