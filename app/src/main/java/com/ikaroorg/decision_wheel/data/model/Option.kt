package com.ikaroorg.decision_wheel.data.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "options")
data class Option (
    @PrimaryKey val id: String,
    val text: String,
    val color: Long
){
    val colorValue: Color
        get() = Color(color.toULong())
}