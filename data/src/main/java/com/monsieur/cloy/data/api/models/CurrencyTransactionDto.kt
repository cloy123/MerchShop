package com.monsieur.cloy.data.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CurrencyTransactionDto(
    @SerializedName("id")
    @Expose
    var id: String,
    @SerializedName("userId")
    @Expose
    var userId: String,
    @SerializedName("date")
    @Expose
    var date: String,
    @SerializedName("currencyTransactionTypeId")
    @Expose
    var currencyTransactionTypeId: Int,
    @SerializedName("points")
    @Expose
    var points: Int
) {
}