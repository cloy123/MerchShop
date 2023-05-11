package com.monsieur.cloy.data.api.models.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.monsieur.cloy.data.api.models.NotificationDto

class GetNotificationsInfoResponse(
    @SerializedName("notifications")
    @Expose
    var notifications: List<NotificationDto>
) {
}