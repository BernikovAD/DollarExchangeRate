package com.example.dollarexchangerate.domain.repository

import com.example.dollarexchangerate.data.RateXml
import com.example.dollarexchangerate.domain.service.IRetrofitAPI

abstract class BaseRepository(iRetrofitAPI: IRetrofitAPI) {
    abstract suspend fun getDollarExchangeRate(dateFrom:String,dateBefore:String): RateXml
}