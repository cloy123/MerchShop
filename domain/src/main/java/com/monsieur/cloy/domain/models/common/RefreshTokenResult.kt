package com.monsieur.cloy.domain.models.common

class RefreshTokenResult(
    val accessToken: String?,
    val refreshToken: String?,
    var isSuccessful: Boolean,
    val code: Int
) {
}