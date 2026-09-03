package com.ikaroorg.decision_wheel.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ikaroorg.decision_wheel.data.model.ListOptions
import kotlinx.coroutines.flow.Flow

@Dao
interface ListOptionDao {
    @Query("SELECT * FROM listOptions")
    fun getAllSavedLists(): Flow<List<ListOptions>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveList(listOption: ListOptions)
    @Query("DELETE FROM listOptions WHERE id = :listId")
    suspend fun deleteListById(listId: String)
}