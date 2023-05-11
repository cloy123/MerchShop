package com.monsieur.cloy.data.storage.models

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "eventParticipants")
class EventParticipantEntity {
    @ColumnInfo(name = "id")
    @NonNull
    @PrimaryKey(autoGenerate = false)
    var id: String = ""

    @ColumnInfo(name = "eventId")
    @NonNull
    var eventId: String = ""

    @ColumnInfo(name = "eventRoleId")
    @NonNull
    var eventRoleId: String = ""

    @ColumnInfo(name = "userId")
    @NonNull
    var userId: String = ""

    @ColumnInfo(name = "isVisit")
    @NonNull
    var isVisit: Boolean = false

    @ColumnInfo(name = "userTypeId")
    @NonNull
    var userTypeId: Int = 0

    @ColumnInfo(name = "firstName")
    @NonNull
    var firstName: String = ""

    @ColumnInfo(name = "lastName")
    @NonNull
    var lastName: String = ""

    @ColumnInfo(name = "email")
    @NonNull
    var email: String = ""

    @ColumnInfo(name = "className")
    @Nullable
    var className: String? = ""

    @ColumnInfo(name = "genderId")
    @NonNull
    var genderId: Int = 0
}