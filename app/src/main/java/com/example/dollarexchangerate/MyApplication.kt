package com.example.dollarexchangerate

import android.app.Application
import android.content.Context
import com.example.dollarexchangerate.di.module.AppComponent
import com.example.dollarexchangerate.di.module.ContextModule
import com.example.dollarexchangerate.di.module.DaggerAppComponent

class MyApplication : Application() {

    init {
        instance = this
    }
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    companion object {

        private var instance: MyApplication? = null
        val appInstance
            get() = instance!!

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

}