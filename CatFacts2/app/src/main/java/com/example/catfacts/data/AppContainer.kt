package com.example.catfacts.data

import android.content.Context
import androidx.room.Room
import com.example.catfacts.data.database.CatsDatabase
import com.example.catfacts.network.NetworkConnectionInterceptor
import com.example.catfacts.network.facts.FactsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * Interface representing the configuration container for the application.
 * This container provides access to various dependencies required for the
 * operation of the application.
 */
interface AppContainer {
    val factRepository: FactRepository
}

/**
 * Default implementation of the [AppContainer] interface that provides
 * dependencies for the application.
 *
 * @param applicationContext The application context used for initializing dependencies.
 */
class DefaultAppContainer(applicationContext: Context) : AppContainer {

    /**
     * Lazily initializes and provides a [FactRepository] implementation using a
     * [CashingFactRepository] with dependencies such as the fact DAO, FactsApiService, and
     * application context.
     */
    override val factRepository: FactRepository by lazy {
        CashingFactRepository(catsDatabase.factDao(), factsApiService, applicationContext)
    }

    // Network Connection Interceptor for checking network availability.
    private val networkCheck = NetworkConnectionInterceptor(applicationContext)

    // OkHttpClient with the network interceptor.
    private val client = OkHttpClient.Builder()
        .addInterceptor(networkCheck)
        .build()

    // Base URL for the Cat Facts API.
    private val baseUrl = "https://meowfacts.herokuapp.com/"

    // Uncomment the line below for local emulator testing.
    // private val baseUrl = "http://10.0.2.2:3000"


    // Retrofit instance for making API calls.
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .client(client)
        .build()

    // Lazy initialization of the FactsApiService using Retrofit.
    private val factsApiService: FactsApiService by lazy {
        retrofit.create(FactsApiService::class.java)
    }

    // Lazy initialization of the CatsDatabase using Room.
    private val catsDatabase: CatsDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            CatsDatabase::class.java,
            "cat-facts",
        ).fallbackToDestructiveMigration()
            .build()
    }
}
