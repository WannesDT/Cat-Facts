package com.example.catfacts.network.facts

import retrofit2.http.GET

/**
 * Interface defining API calls for fetching facts.
 */
interface FactsApiService {
    /**
     * Fetches facts from the API.
     *
     * @return An instance of [ApiFacts] containing the retrieved facts.
     */
    @GET(".")
    suspend fun getFacts(): ApiFacts
}

