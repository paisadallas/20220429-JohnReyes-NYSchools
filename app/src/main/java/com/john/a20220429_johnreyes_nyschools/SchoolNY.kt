package com.john.a20220429_johnreyes_nyschools

import android.app.Application
import com.john.a20220429_johnreyes_nyschools.di.networkModule
import com.john.a20220429_johnreyes_nyschools.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SchoolNY:Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SchoolNY)
            modules(listOf(networkModule, viewModelModule))
        }
    }
}