package com.monsieur.cloy.data.storage.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "events")
class EventEntity {
    @ColumnInfo(name = "id")
    @NonNull
    @PrimaryKey(autoGenerate = false)
    var id: String = ""

    @ColumnInfo(name = "date")
    @NonNull
    var date: LocalDateTime = LocalDateTime.now()

    @ColumnInfo(name = "name")
    @NonNull
    var name: String = ""

    @ColumnInfo(name = "isCompleted")
    @NonNull
    var isCompleted: Boolean = false

    @ColumnInfo(name = "description")
    @NonNull
    var description: String = ""

    @ColumnInfo(name = "availableFor")
    @NonNull
    var availableFor: List<String> = ArrayList()
}