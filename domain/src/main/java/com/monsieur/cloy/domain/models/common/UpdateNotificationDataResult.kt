package com.monsieur.cloy.domain.models.common

import com.monsieur.cloy.domain.models.Notification

class UpdateNotificationDataResult(
    val notifications: List<Notification>?,
    val isSuccessful: Boolean,
    val code: Int
) {
}