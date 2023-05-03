package com.monsieur.cloy.domain.models.common

import com.monsieur.cloy.domain.models.User

class LoginResult(
    val user: User?,
    val isUserFound: Boolean,
    val isPasswordCorrect: Boolean,
    val isAccess: Boolean,
    val isSuccessful: Boolean) {
}