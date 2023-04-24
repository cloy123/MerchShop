package com.monsieur.cloy.data.api.models.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.monsieur.cloy.data.api.models.UserDto

class LoginResponse(
    @SerializedName("isUserFound")
    @Expose
    var isUserFound: Boolean,
    @SerializedName("isPasswordCorrect")
    @Expose
    var isPasswordCorrect: Boolean,
    @SerializedName("isAccess")
    @Expose
    var isAccess: Boolean,
    @SerializedName("accessToken")
    @Expose
    var accessToken: String?,
    @SerializedName("userDto")
    @Expose
    var userData: UserDto?,
    @SerializedName("refreshToken")
    @Expose
    var refreshToken: String?
){}