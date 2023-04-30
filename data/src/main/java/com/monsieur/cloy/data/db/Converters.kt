package com.monsieur.cloy.data.db

import androidx.room.TypeConverter
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
}