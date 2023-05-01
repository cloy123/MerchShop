package com.monsieur.cloy.domain.models

import java.time.LocalDateTime

class Notification(
    var id: String,
    var message: String,
    var isSend: Boolean,
    var dateTime: LocalDateTime
) {
}