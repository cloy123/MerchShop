package com.monsieur.cloy.data.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserDto(
    @SerializedName("id")
    @Expose
    var id: String,
    @SerializedName("isAccess")
    @Expose
    var isAccess: Boolean,
    @SerializedName("userTypeId")
    @Expose
    var userTypeId: Int,
    @SerializedName("userTypeName")
    @Expose
    var userTypeName: String,
    @SerializedName("firstName")
    @Expose
    var firstName: String,
    @SerializedName("lastName")
    @Expose
    var lastName: String,
    @SerializedName("fullName")
    @Expose
    var fullName: String,
    @SerializedName("userFullInfo")
    @Expose
    var userFullInfo: String,
    @SerializedName("birthday")
    @Expose
    var birthday: String,
    @SerializedName("email")
    @Expose
    var email: String,
    @SerializedName("pointBalance")
    @Expose
    var pointBalance: Int,
    @SerializedName("classNumber")
    @Expose
    var classNumber: Int?,
    @SerializedName("classLetter")
    @Expose
    var classLetter: String?,
    @SerializedName("genderId")
    @Expose
    var genderId: Int,
    @SerializedName("genderName")
    @Expose
    var genderName: String
) {
}