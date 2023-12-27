package com.example.catfacts.network.facts

import retrofit2.http.GET

interface FactsApiService {
    @GET(".")
    suspend fun getFacts(): ApiFacts
}

