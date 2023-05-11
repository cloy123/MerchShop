package com.monsieur.cloy.domain.models.common

class FinishEventResult(
    var isSuccessful: Boolean,
    var isFinished: Boolean,
    var errorMessage: String,
    var code: Int
) {
}