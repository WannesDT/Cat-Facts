package com.example.catfacts.fake

import com.example.catfacts.network.facts.ApiFacts
import com.example.catfacts.network.facts.FactsApiService

/**
 * A fake implementation of [FactsApiService] for testing purposes.
 * This implementation returns predefined [ApiFacts] data from [FakeDataSource].
 */
class FakeFactsApiService : FactsApiService {
    /**
     * Returns predefined [ApiFacts] data containing a list of favorite facts.
     *
     * @return Predefined [ApiFacts] data from [FakeDataSource].
     */
    override suspend fun getFacts(): ApiFacts {
        return ApiFacts(listOf(FakeDataSource.fact1.content))
    }
}
