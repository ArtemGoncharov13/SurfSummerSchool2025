package ru.adgoncharov.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Date

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromDate(date: Date?): Long? = date?.time

    @TypeConverter
    fun toDate(timestamp: Long?): Date? = timestamp?.let { Date(it) }

    @TypeConverter
    fun fromNullableStringList(list: List<String?>?): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toNullableStringList(json: String): List<String?> {
        val type = object : TypeToken<List<String?>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromListOfLists(list: List<List<String>>?): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toListOfLists(json: String): List<List<String>> {
        val type = object : TypeToken<List<List<String>>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromStringList(list: List<String>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toStringList(json: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(json, type)
    }
}