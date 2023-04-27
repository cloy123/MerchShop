package com.monsieur.cloy.data.api.models.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.monsieur.cloy.data.api.models.CurrencyTransactionDto
import com.monsieur.cloy.data.api.models.NotificationDto

class GetCurrencyTransactionsInfoResponse(
    @SerializedName("currencyTransactions")
    @Expose
    var currencyTransactions: List<CurrencyTransactionDto>
) {
}