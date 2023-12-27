package com.example.catfacts.fake

import com.example.catfacts.data.Fact
import com.example.catfacts.data.FactRepository
import com.example.catfacts.data.toFact
import com.example.catfacts.data.toFacts
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

class FakeApiFactsRepository : FactRepository {
    override fun getFavoriteFacts(): Flow<List<Fact>> {
        return listOf(FakeDataSource.favoFacts.facts.toFacts()).asFlow()
    }

    override suspend fun getRandomFact(): Fact {
        return FakeDataSource.fact1.toFact()
    }

    override suspend fun insertFavoriteFact(fact: Fact) {
        //
    }

    override suspend fun deleteFavoriteFact(fact: Fact) {
        TODO("Not yet implemented")
    }
}
