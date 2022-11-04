package com.example.dollarexchangerate.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dollarexchangerate.data.Rate


@Database(entities = [Rate::class], version = 4)
abstract class RateDataBase:RoomDatabase() {
    abstract fun rateDao(): RateDao

    companion object {
        @Volatile
        private var database: RateDataBase? = null

        @Synchronized
        fun getInstance(context: Context): RateDataBase {
            return if (database == null) {
                database = Room.databaseBuilder(
                    context, RateDataBase::class.java,
                    "database"
                ).build()
                database as RateDataBase
            } else database as RateDataBase
        }
    }
}