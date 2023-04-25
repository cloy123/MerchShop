package com.monsieur.cloy.data.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EventDto(
    @SerializedName("id")
    @Expose
    var id: String,
    @SerializedName("date")
    @Expose
    var date: String,
    @SerializedName("name")
    @Expose
    var name: String,
    @SerializedName("description")
    @Expose
    var description: String,
    @SerializedName("availableFor")
    @Expose
    var availableFor: List<String>,
    @SerializedName("eventResponsibles")
    @Expose
    var eventResponsibles: List<EventResponsibleDto>,
    @SerializedName("eventRoles")
    @Expose
    var eventRoles: List<EventRoleDto>,
    @SerializedName("eventParticipants")
    @Expose
    var eventParticipants: List<EventParticipantDto>
) {
}