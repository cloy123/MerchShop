package com.monsieur.cloy.domain.models

import java.time.LocalDateTime

class CurrencyTransaction(
    val id: Int,
    val guid: String,
    val dateTime: LocalDateTime,
    val points: Int
    ) {
}