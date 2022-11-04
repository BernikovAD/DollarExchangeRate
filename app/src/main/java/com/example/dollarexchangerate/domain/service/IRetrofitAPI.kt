package com.example.dollarexchangerate.domain.service

import com.example.dollarexchangerate.data.RateXml
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IRetrofitAPI {
    @GET("/scripts/XML_dynamic.asp")
    suspend fun getDollarExchangeRate(
        @Query("date_req1") dateFrom: String,
        @Query("date_req2") dateBefore: String,
        @Query("VAL_NM_RQ") rate: String = "R01235"
        ): RateXml
}