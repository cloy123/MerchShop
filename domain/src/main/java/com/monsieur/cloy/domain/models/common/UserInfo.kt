package com.monsieur.cloy.domain.models.common

import java.time.LocalDateTime

class UserInfo(
    var id: String,
    var userTypeId: Int,
    var firstName: String,
    var lastName: String,
    var birthday: LocalDateTime,
    var email: String,
    var pointBalance: Int,
    var className: String?,
    var genderId: Int,
) {
}