package com.monsieur.cloy.domain.models

import java.time.LocalDateTime

class Order(
    var id: String,
    var dateCreation: LocalDateTime,
    var dateCompletion: LocalDateTime?,
    var statusId: Int,
    var orderSum: Int
) {
}