package com.monsieur.cloy.domain.models.common

class CreateOrderResult(
    var isSuccessful: Boolean,
    var isCreated: Boolean,
    val errorMessage: String,
    var code: Int
) {
}