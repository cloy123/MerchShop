package com.monsieur.cloy.data.api.models.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SignupEventResponse(
    @SerializedName("isSigned")
    @Expose
    var isSigned: Boolean,
    @SerializedName("errorMessage")
    @Expose
    var errorMessage: String
) {
}