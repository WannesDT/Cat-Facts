package com.example.catfacts.fake

import com.example.catfacts.data.Fact
import com.example.catfacts.data.FactRepository
import com.example.catfacts.data.toFacts
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeApiFactsRepository : FactRepository {
    override fun getFavoriteFacts(): Flow<List<Fact>> {
        val coldFlow = flow {
            emit(FakeDataSource.favoFacts.facts.toFacts())
        }
        return coldFlow
    }

    override suspend fun getRandomFact(): Fact {
        return FakeDataSource.fact1
    }

    override suspend fun insertFavoriteFact(fact: Fact) {
        // do nothing :)
    }

    override suspend fun deleteFavoriteFact(fact: Fact) {
        // do nothing :)
    }
}
