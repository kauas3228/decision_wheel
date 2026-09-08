package com.ikaroorg.decision_wheel.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.ikaroorg.decision_wheel.data.model.Option
import kotlinx.coroutines.flow.Flow

@Dao
interface OptionDao {
    @Query("SELECT * FROM options")
    fun getAllOptions(): Flow<List<Option>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOption(option: Option)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllOptions(options: List<Option>)
    @Query("DELETE FROM options WHERE id = :optionId")
    suspend fun deleteOption(optionId: String)
    @Query("DELETE FROM options")
    suspend fun deleteAllOptions()
    @Transaction
    suspend fun replaceAllOptions(newOptions: List<Option>) {
        deleteAllOptions()
        insertAllOptions(newOptions)
    }
    @Query("UPDATE options SET isAvailable = 0 WHERE id = :optionId")
    suspend fun markAsDraw(optionId: String)
    @Query("UPDATE options SET isAvailable = 1")
    suspend fun resetAllAvailability()
}