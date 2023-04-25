package com.monsieur.cloy.data.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OrderItemDto(
    @SerializedName("id")
    @Expose
    var id: String,
    @SerializedName("orderId")
    @Expose
    var orderId: String,
    @SerializedName("productId")
    @Expose
    var productId: String,
    @SerializedName("quantity")
    @Expose
    var quantity: Int,
    @SerializedName("price")
    @Expose
    var price: Int
) {
}