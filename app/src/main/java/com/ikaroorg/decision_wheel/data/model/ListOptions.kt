package com.ikaroorg.decision_wheel.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Entity(tableName = "listOptions")
@TypeConverters(ListOptionConverters::class)
data class ListOptions (
    @PrimaryKey val id: String,
    val title: String,
    val options: List<Option>
)

class ListOptionConverters {
    @TypeConverter
    fun fromOptionList(options: List<Option>): String {
        return Json.encodeToString(options)
    }

    @TypeConverter
    fun toOptionsList(options: String): List<Option> {
        return Json.decodeFromString(options)
    }
}