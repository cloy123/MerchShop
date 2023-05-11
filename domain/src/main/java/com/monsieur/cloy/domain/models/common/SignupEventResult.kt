package com.monsieur.cloy.domain.models.common

class SignupEventResult(
    var isSuccessful: Boolean,
    var isSigned: Boolean,
    var errorMessage: String,
    var code: Int
) {

}