package com.monsieur.cloy.domain.models.common

class CancelOrderResult(
    var isSuccessful: Boolean,
    var isCanceled: Boolean,
    var errorMessage: String,
    var code: Int
) {
}