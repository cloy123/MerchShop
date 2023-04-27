package com.monsieur.cloy.data.storage.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.monsieur.cloy.data.api.models.EventParticipantDto
import com.monsieur.cloy.data.api.models.EventResponsibleDto
import com.monsieur.cloy.data.api.models.EventRoleDto
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

    @ColumnInfo(name = "description")
    @NonNull
    var description: String = ""

    @ColumnInfo(name = "availableFor")
    @NonNull
    var availableFor: List<String> = ArrayList()
}