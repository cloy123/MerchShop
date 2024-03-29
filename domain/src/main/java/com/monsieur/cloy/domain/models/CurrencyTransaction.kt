package com.monsieur.cloy.domain.models

import java.time.LocalDateTime

class CurrencyTransaction(
    var id: String,
    var date: LocalDateTime,
    var currencyTransactionTypeId: Int,
    var points: Int
) {
}