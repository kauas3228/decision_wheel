package com.ikaroorg.decision_wheel.data.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Entity(tableName = "options")
@TypeConverters(OptionConverters::class)
data class Option (
    @PrimaryKey val id: String,
    val text: String,
    val color: Color
)
class OptionConverters {
    @TypeConverter
    fun fromColor(color: Color): Long = color.value.toLong()
    @TypeConverter
    fun toColor(value: Long): Color = Color(value.toULong())
}