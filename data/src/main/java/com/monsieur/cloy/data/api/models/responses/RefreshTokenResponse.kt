package com.monsieur.cloy.data.api.models.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RefreshTokenResponse(
    @SerializedName("accessToken")
    @Expose
    var accessToken: String?,
    @SerializedName("refreshToken")
    @Expose
    var refreshToken: String?
) {
}