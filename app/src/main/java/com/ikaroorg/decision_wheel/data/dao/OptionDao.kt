package com.ikaroorg.decision_wheel.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ikaroorg.decision_wheel.data.model.Option
import kotlinx.coroutines.flow.Flow

@Dao
interface OptionDao {
    @Query("SELECT * FROM options")
    fun getAllOptions(): Flow<List<Option>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOption(option: Option)
    @Query("DELETE FROM options WHERE id = :optionId")
    suspend fun deleteOption(optionId: String)
}