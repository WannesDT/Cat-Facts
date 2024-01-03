package com.example.catfacts.fake

import com.example.catfacts.data.Fact
import com.example.catfacts.data.FactRepository
import com.example.catfacts.data.database.asDomainFacts
import com.example.catfacts.data.toFacts
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * A fake implementation of [FactRepository] for testing purposes.
 * This implementation returns predefined data from [FakeDataSource].
 */
class FakeApiFactsRepository(private val apiService: FakeFactsApiService) : FactRepository {

    override fun getFavoriteFacts(): Flow<List<Fact>> {
        val coldFlow = flow {
            emit(FakeDataSource.favoFacts.asDomainFacts())
        }
        return coldFlow
    }

    override suspend fun getRandomFact(): Fact {
        return apiService.getFacts().facts.toFacts().first()
    }

    override suspend fun insertFavoriteFact(fact: Fact) {
        // do nothing :)
    }

    override suspend fun deleteFavoriteFact(fact: Fact) {
        // do nothing :)
    }
}
