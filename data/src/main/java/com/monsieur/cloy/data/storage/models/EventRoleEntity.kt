package com.monsieur.cloy.data.storage.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "eventRoles")
class EventRoleEntity {

    @ColumnInfo(name = "id")
    @NonNull
    @PrimaryKey(autoGenerate = false)
    var id: String = ""

    @ColumnInfo(name = "name")
    @NonNull
    var name: String = ""

    @ColumnInfo(name = "userTypeId")
    @NonNull
    var userTypeId: Int = 0

    @ColumnInfo(name = "eventId")
    @NonNull
    var eventId: String = ""

    @ColumnInfo(name = "prize")
    @NonNull
    var prize: Int = 0
}