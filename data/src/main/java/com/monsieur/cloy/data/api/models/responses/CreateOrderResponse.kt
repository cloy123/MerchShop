package com.monsieur.cloy.data.api.models.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CreateOrderResponse(
    @SerializedName("isCreated")
    @Expose
    var isCreated: Boolean,
    @SerializedName("errorMessage")
    @Expose
    var errorMessage: String,
    @SerializedName("id")
    @Expose
    var id: String
) {
}