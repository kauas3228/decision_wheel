package com.ikaroorg.decision_wheel.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ikaroorg.decision_wheel.data.dao.OptionDao
import com.ikaroorg.decision_wheel.data.model.Option

@Database(entities = [Option::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun optionDao(): OptionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "decision_wheel_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}