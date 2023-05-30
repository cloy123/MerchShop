package com.monsieur.cloy.data.api.models.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NoteVisitResponse(
    @SerializedName("isNoted")
    @Expose
    var isNoted: Boolean,
    @SerializedName("errorMessage")
    @Expose
    var errorMessage: String
) {
}