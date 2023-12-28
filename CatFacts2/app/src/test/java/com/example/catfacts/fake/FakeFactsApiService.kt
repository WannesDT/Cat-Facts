package com.example.catfacts.fake

import com.example.catfacts.network.facts.ApiFacts
import com.example.catfacts.network.facts.FactsApiService

class FakeFactsApiService : FactsApiService {
    override suspend fun getFacts(): ApiFacts {
        return FakeDataSource.favoFacts
    }
}
