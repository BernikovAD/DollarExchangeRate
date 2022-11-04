package com.example.dollarexchangerate.view

import com.example.dollarexchangerate.utils.IViewModelState
import com.example.dollarexchangerate.data.RateXml

sealed class DollarExchangeRateState : IViewModelState {
    object Loading : DollarExchangeRateState()
    data class SuccessLoadData(val list: RateXml) : DollarExchangeRateState()
}