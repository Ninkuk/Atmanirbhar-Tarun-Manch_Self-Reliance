package com.ninkuk.atmanirbharbharat_tarunmanch.data

import androidx.room.TypeConverter

class DatabaseTypeConverter {

    @TypeConverter
    fun listToString(phoneNumbers: List<String>): String {
        return phoneNumbers.joinToString(separator = ", ")
    }

    @TypeConverter
    fun stringToList(phoneNumbers: String): List<String> {
        return phoneNumbers.split(", ")
    }
}