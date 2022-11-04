package com.example.dollarexchangerate.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dollarexchangerate.data.Rate

@Dao
interface RateDao {
    @Query("SELECT * FROM rate")
    suspend fun getRate(): Rate

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(record: Rate)
}