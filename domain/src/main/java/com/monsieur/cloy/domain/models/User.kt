package com.monsieur.cloy.domain.models

class User(
    val id: Int,
    val guid: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val birthday: String,
    val pointBalance: Int,
    val className: String,
    val token: String
) {
}