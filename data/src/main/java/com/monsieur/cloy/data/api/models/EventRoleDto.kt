package com.monsieur.cloy.data.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EventRoleDto(
    @SerializedName("id")
    @Expose
    var id: String,
    @SerializedName("name")
    @Expose
    var name: String,
    @SerializedName("userTypeId")
    @Expose
    var userTypeId: Int,
    @SerializedName("eventId")
    @Expose
    var eventId: String,
    @SerializedName("prize")
    @Expose
    var prize: Int
) {
}