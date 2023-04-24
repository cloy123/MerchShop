package com.monsieur.cloy.data.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProductColorDto(
    @SerializedName("id")
    @Expose
    var id: String,

    @SerializedName("name")
    @Expose
    var name: String,

    @SerializedName("value")
    @Expose
    var value: String
) {
}