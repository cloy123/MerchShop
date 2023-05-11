package com.monsieur.cloy.data.storage.models

import androidx.room.ColumnInfo

class EventParticipantWithRole {

    @ColumnInfo(name = "id")
    var id: String = ""

    @ColumnInfo(name = "eventId")
    var eventId: String = ""

    @ColumnInfo(name = "eventRoleId")
    var eventRoleId: String = ""

    @ColumnInfo(name = "userId")
    var userId: String = ""

    @ColumnInfo(name = "isVisit")
    var isVisit: Boolean = false

    @ColumnInfo(name = "userTypeId")
    var userTypeId: Int = 0

    @ColumnInfo(name = "firstName")
    var firstName: String = ""

    @ColumnInfo(name = "lastName")
    var lastName: String = ""

    @ColumnInfo(name = "email")
    var email: String = ""

    @ColumnInfo(name = "className")
    var className: String? = ""

    @ColumnInfo(name = "genderId")
    var genderId: Int = 0

    @ColumnInfo(name = "name")
    var roleName: String = ""

    @ColumnInfo(name = "prize")
    var prize: Int = 0
}