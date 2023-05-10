package com.monsieur.cloy.domain.models

import java.time.LocalDateTime

class Event(
    var id: String,
    var date: LocalDateTime,
    var name: String,
    var description: String,
    var availableFor: List<String>,
    var isCompleted: Boolean
) {
}