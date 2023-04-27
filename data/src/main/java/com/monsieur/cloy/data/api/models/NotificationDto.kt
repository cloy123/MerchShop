package com.monsieur.cloy.data.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NotificationDto(
    @SerializedName("id")
    @Expose
    var id: String,
    @SerializedName("userId")
    @Expose
    var userId: String,
    @SerializedName("message")
    @Expose
    var message: String,
    @SerializedName("isSend")
    @Expose
    var isSend: Boolean,
    @SerializedName("dateTime")
    @Expose
    var dateTime: String
) {
}