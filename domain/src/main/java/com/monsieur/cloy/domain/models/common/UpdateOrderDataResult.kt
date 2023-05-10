package com.monsieur.cloy.domain.models.common

import com.monsieur.cloy.domain.models.Order
import com.monsieur.cloy.domain.models.OrderItem

class UpdateOrderDataResult(
    val orders: List<Order>?,
    val orderItems: List<OrderItem>?,
    val isSuccessful: Boolean,
    val code: Int){
}