package com.monsieur.cloy.data.storage.models

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "orders")
class OrderEntity {

    @ColumnInfo(name = "id")
    @NonNull
    @PrimaryKey(autoGenerate = false)
    var id: String = ""

    @ColumnInfo(name = "dateCreation")
    @NonNull
    var dateCreation: LocalDateTime = LocalDateTime.now()

    @ColumnInfo(name = "dateCompletion")
    @Nullable
    var dateCompletion: LocalDateTime? = LocalDateTime.now()

    @ColumnInfo(name = "statusId")
    @NonNull
    var statusId: Int = 0

    @ColumnInfo(name = "orderSum")
    @NonNull
    var orderSum: Int = 0
}