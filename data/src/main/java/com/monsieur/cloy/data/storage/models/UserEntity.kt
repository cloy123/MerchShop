package com.monsieur.cloy.data.storage.models

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
class UserEntity {
    @ColumnInfo(name = "userId")
    @NonNull
    @PrimaryKey(autoGenerate = false)
    var id: String = ""

    @ColumnInfo(name = "userTypeId")
    @NonNull
    var userTypeId: Int = 0

    @ColumnInfo(name = "firstName")
    @NonNull
    var firstName: String = ""

    @ColumnInfo(name = "lastName")
    @NonNull
    var lastName: String = ""

    @ColumnInfo(name = "birthday")
    @NonNull
    var birthday: String = ""

    @ColumnInfo(name = "email")
    @NonNull
    var email: String = ""

    @ColumnInfo(name = "pointBalance")
    @NonNull
    var pointBalance: Int = 0

    @ColumnInfo(name = "className")
    @Nullable
    var className: String? = ""

    @ColumnInfo(name = "genderId")
    @NonNull
    var genderId: Int = 0

    @ColumnInfo(name = "accessToken")
    @NonNull
    var accessToken: String = ""

    @ColumnInfo(name = "refreshToken")
    @NonNull
    var refreshToken: String = ""
}