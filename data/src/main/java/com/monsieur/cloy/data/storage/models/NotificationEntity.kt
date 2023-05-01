package com.monsieur.cloy.data.storage.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

@Entity(tableName = "notifications")
class NotificationEntity {

    @ColumnInfo(name = "id")
    @NonNull
    @PrimaryKey(autoGenerate = false)
    var id: String = ""

    @ColumnInfo(name = "message")
    @NonNull
    var message: String = ""

    @ColumnInfo(name = "isSend")
    @NonNull
    var isSend: Boolean = false

    @ColumnInfo(name = "dateTime")
    @NonNull
    var dateTime: LocalDateTime = LocalDateTime.now()
}