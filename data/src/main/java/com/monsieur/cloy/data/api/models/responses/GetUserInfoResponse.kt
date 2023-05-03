package com.monsieur.cloy.data.api.models.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.monsieur.cloy.data.api.models.UserDto

class GetUserInfoResponse(
    @SerializedName("user")
    @Expose
    var user: UserDto?
) {
}