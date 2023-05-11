package com.monsieur.cloy.domain.models

import java.time.LocalDateTime

class User(
    var id: String,
    var userTypeId: Int,
    var firstName: String,
    var lastName: String,
    var birthday: LocalDateTime,
    var email: String,
    var pointBalance: Int,
    var className: String?,
    var genderId: Int,
    var accessToken: String,
    var refreshToken: String
) {
}