package com.example.dollarexchangerate.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.dollarexchangerate.view.ViewModelFactory
import com.example.dollarexchangerate.domain.repository.RepositoryDataBase
import com.example.dollarexchangerate.domain.repository.RepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {
    @Provides
    fun viewModelFactory(repository: RepositoryImpl,repositoryDataBase: RepositoryDataBase): ViewModelProvider.Factory {
        return ViewModelFactory(repository = repository,repositoryDataBase)
    }
}