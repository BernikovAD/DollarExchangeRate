package com.example.dollarexchangerate.domain.repository

import com.example.dollarexchangerate.data.RateXml
import com.example.dollarexchangerate.domain.service.IRetrofitAPI
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val service: IRetrofitAPI) : BaseRepository(service){
    override suspend fun getDollarExchangeRate(dateFrom: String, dateBefore: String): RateXml {
        return  service.getDollarExchangeRate(dateFrom,dateBefore)
    }
}