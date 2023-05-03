package com.monsieur.cloy.domain.models.common

class GetUserInfoResult(
    var userInfo: UserInfo?,
    var isSuccessful: Boolean,
    var code: Int
) {

}