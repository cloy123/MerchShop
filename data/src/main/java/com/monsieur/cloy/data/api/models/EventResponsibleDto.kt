package com.monsieur.cloy.data.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EventResponsibleDto(
    @SerializedName("id")
    @Expose
    var id: String,
    @SerializedName("eventId")
    @Expose
    var eventId: String,
    @SerializedName("userId")
    @Expose
    var userId: String,
    @SerializedName("userTypeId")
    @Expose
    var userTypeId: Int,
    @SerializedName("firstName")
    @Expose
    var firstName: String,
    @SerializedName("lastName")
    @Expose
    var lastName: String,
    @SerializedName("email")
    @Expose
    var email: String,
    @SerializedName("classNumber")
    @Expose
    var classNumber: Int?,
    @SerializedName("classLetter")
    @Expose
    var classLetter: String?,
    @SerializedName("genderId")
    @Expose
    var genderId: Int
) {
}