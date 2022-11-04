package com.example.dollarexchangerate.view

import androidx.lifecycle.viewModelScope
import com.app.peshkariki.Fragment.base.BaseViewModel
import com.example.dollarexchangerate.data.Rate
import com.example.dollarexchangerate.data.Record
import com.example.dollarexchangerate.domain.repository.RepositoryDataBase
import com.example.dollarexchangerate.domain.repository.RepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DollarExchangeRateViewModel @Inject constructor(private val repository: RepositoryImpl,private val repositoryDataBase: RepositoryDataBase):
BaseViewModel<DollarExchangeRateState>(DollarExchangeRateState.Loading){


 fun getData(dateFrom:String,dateBefore:String){
    viewModelScope.launch(Dispatchers.IO) {
        val response = repository.getDollarExchangeRate(dateFrom,dateBefore)
        state.postValue(DollarExchangeRateState.SuccessLoadData(response))
        saveDB(response.rateList!!.last())
    }
}
    private fun saveDB(record:Record){
        viewModelScope.launch(Dispatchers.IO) {
            repositoryDataBase.save(Rate(record.date!!,record.nominal!!,record.value!!))
        }
    }

}