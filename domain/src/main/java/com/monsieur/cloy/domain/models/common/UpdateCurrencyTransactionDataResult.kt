package com.monsieur.cloy.domain.models.common

import com.monsieur.cloy.domain.models.CurrencyTransaction
import com.monsieur.cloy.domain.models.Product

class UpdateCurrencyTransactionDataResult(
    val currencyTransactions: List<CurrencyTransaction>?,
    val isSuccessful: Boolean,
    val code: Int
) {
}