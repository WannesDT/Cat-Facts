package com.example.catfacts.data

import android.content.Context
import androidx.room.Room
import com.example.catfacts.data.database.CatsDatabase
import com.example.catfacts.network.facts.FactsApiService
import com.example.catfacts.network.NetworkConnectionInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

interface AppContainer {
    val factRepository: FactRepository
}

class DefaultAppContainer(applicationContext: Context) : AppContainer {
    override val factRepository: FactRepository by lazy {
        CashingFactRepository(catsDatabase.factDao(), factsApiService, applicationContext)
    }

    private val networkCheck = NetworkConnectionInterceptor(applicationContext)

    private val client = OkHttpClient.Builder()
        .addInterceptor(networkCheck)
        .build()

    private val baseUrl = "https://meowfacts.herokuapp.com/"

    // voor emulator lokaal
    // private val baseUrl = "http://10.0.2.2:3000"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .client(client)
        .build()

    private val factsApiService: FactsApiService by lazy {
        retrofit.create(FactsApiService::class.java)
    }



    private val catsDatabase: CatsDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            CatsDatabase::class.java,
            "cat-facts",
        ).fallbackToDestructiveMigration()
            .build()
    }
}
