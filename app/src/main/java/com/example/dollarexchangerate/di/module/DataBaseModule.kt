package com.example.dollarexchangerate.di.module

import android.content.Context
import com.example.dollarexchangerate.database.RateDao
import com.example.dollarexchangerate.database.RateDataBase
import com.example.dollarexchangerate.domain.repository.RepositoryDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {
    @Provides
    @Singleton
    fun provideRepositoryDataBase(dataBase: RateDataBase): RepositoryDataBase {
        return RepositoryDataBase(database = dataBase)
    }

    @Provides
    @Singleton
    fun provideCurrencyDao(dataBase: RateDataBase): RateDao {
        return dataBase.rateDao()
    }

    @Provides
    @Singleton
    fun provideDataBase(context: Context): RateDataBase {
        return RateDataBase.getInstance(context)
    }
}