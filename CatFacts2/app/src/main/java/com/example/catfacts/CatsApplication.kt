package com.example.catfacts

import android.app.Application
import com.example.catfacts.data.AppContainer
import com.example.catfacts.data.DefaultAppContainer

class CatsApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(applicationContext)
    }
}
