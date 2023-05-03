package com.monsieur.cloy.data.db

import android.util.JsonWriter
import androidx.room.TypeConverter
import com.google.gson.Gson
import java.time.LocalDateTime

class Converters {
    @TypeConverter
    fun localDateTimeToJson(value: LocalDateTime?): String{
        if(value == null){
            return "null"
        }
        return value.toString()
    }

    @TypeConverter
    fun jsonToLocalDateTime(value: String): LocalDateTime?{
        if(value == "null"){
            return null
        }
        return try {
            LocalDateTime.parse(value)
        }catch (e: Exception){
            null
        }
    }

    @TypeConverter
    fun stringListToJson(value: List<String>?): String{
        if(value == null){
            return "null"
        }
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToStringList(value: String): List<String>?{
        if(value == "null"){
            return null
        }
        return try {
            Gson().fromJson<Array<String>>(value, Array<String>::class.java).toList()
        }catch (e: Exception){
            null
        }
    }
}