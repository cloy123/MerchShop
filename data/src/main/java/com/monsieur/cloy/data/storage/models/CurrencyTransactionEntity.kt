package com.monsieur.cloy.data.storage.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

@Entity(tableName = "currencyTransactions")
class CurrencyTransactionEntity {

    @ColumnInfo(name = "id")
    @NonNull
    @PrimaryKey(autoGenerate = false)
    var id: String = ""

    @ColumnInfo(name = "date")
    @NonNull
    var date: LocalDateTime = LocalDateTime.now()

    @ColumnInfo(name = "currencyTransactionTypeId")
    @NonNull
    var currencyTransactionTypeId: Int = 0

    @ColumnInfo(name = "points")
    @NonNull
    var points: Int = 0
}