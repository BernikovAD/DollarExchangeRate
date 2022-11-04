package com.example.dollarexchangerate.di.module

import android.content.Context
import com.example.dollarexchangerate.view.DollarExchangeRateFragment
import com.example.dollarexchangerate.view.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [NetworkModule::class, ViewModelModule::class,DataBaseModule::class]
)
@Singleton
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(fragment: DollarExchangeRateFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}

