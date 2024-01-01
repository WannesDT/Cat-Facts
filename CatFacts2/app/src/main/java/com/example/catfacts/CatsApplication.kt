package com.example.catfacts

import android.app.Application
import com.example.catfacts.data.AppContainer
import com.example.catfacts.data.DefaultAppContainer

/**
 * Custom Application class for initializing the Cat Facts application.
 */
class CatsApplication : Application() {
    /**
     * The application container providing dependencies and services.
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(applicationContext)
    }
}
