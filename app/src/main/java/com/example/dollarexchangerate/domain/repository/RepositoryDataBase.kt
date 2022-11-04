package com.example.dollarexchangerate.domain.repository

import com.example.dollarexchangerate.database.RateDataBase
import com.example.dollarexchangerate.data.Rate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryDataBase @Inject constructor(val database: RateDataBase) {
    private val rateDao = database.rateDao()
    suspend fun save(record: Rate) {
        withContext(Dispatchers.IO) {
            rateDao.save(record = record)
        }
    }
    suspend fun getRate(): Rate {
        return withContext(Dispatchers.IO) {
           rateDao.getRate()
        }
    }
}