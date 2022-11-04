package com.example.dollarexchangerate.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dollarexchangerate.domain.repository.RepositoryDataBase
import com.example.dollarexchangerate.domain.repository.RepositoryImpl
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val repository: RepositoryImpl,private val repositoryDataBase: RepositoryDataBase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DollarExchangeRateViewModel(repository,repositoryDataBase) as T
    }
}