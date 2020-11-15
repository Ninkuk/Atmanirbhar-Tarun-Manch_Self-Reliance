package com.ninkuk.atmanirbharbharat_tarunmanch.data

import android.content.Context
import androidx.room.*

@Database(entities = [Business::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseTypeConverter::class)
abstract class BusinessDatabase : RoomDatabase() {

    abstract fun businessDao(): BusinessDao

    companion object {
        @Volatile
        private var INSTANCE: BusinessDatabase? = null

        fun getDatabase(context: Context): BusinessDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BusinessDatabase::class.java,
                    "business_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}