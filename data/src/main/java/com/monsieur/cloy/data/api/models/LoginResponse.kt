package com.monsieur.cloy.data.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

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
    @SerializedName("token")
    @Expose
    var token: String,
    @SerializedName("userDto")
    @Expose
    var userData: UserResponse
){}