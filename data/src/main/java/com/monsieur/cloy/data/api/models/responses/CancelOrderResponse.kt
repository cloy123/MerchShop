package com.monsieur.cloy.data.api.models.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CancelOrderResponse(
    @SerializedName("isCanceled")
    @Expose
    var isCanceled: Boolean,
    @SerializedName("errorMessage")
    @Expose
    var errorMessage: String
) {
}