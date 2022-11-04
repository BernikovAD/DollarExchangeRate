package com.example.dollarexchangerate.di.module

import android.content.Context
import com.example.dollarexchangerate.MyApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule(private val myApplication: MyApplication) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return myApplication.applicationContext
    }
}