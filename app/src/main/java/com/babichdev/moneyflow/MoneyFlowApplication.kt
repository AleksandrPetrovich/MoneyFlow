package com.babichdev.moneyflow

import android.app.Application
import com.babichdev.moneyflow.di.AppContainer

class MoneyFlowApplication : Application() {

    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}