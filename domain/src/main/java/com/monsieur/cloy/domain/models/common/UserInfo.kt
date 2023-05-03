package com.monsieur.cloy.domain.models.common

class UserInfo(
    var id: String,
    var userTypeId: Int,
    var firstName: String,
    var lastName: String,
    var birthday: String,
    var email: String,
    var pointBalance: Int,
    var className: String?,
    var genderId: Int,
) {
}