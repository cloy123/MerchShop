package com.monsieur.cloy.data.api.models.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FinishEventResponse(
    @SerializedName("isFinished")
    @Expose
    var isFinished: Boolean,
    @SerializedName("errorMessage")
    @Expose
    var errorMessage: String
) {
}