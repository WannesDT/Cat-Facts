package com.example.catfacts.data

import android.content.Context
import com.example.catfacts.data.database.FactDao
import com.example.catfacts.data.database.asDbFact
import com.example.catfacts.data.database.asDomainFacts
import com.example.catfacts.network.facts.FactsApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface FactRepository {
    fun getFavoriteFacts(): Flow<List<Fact>>

    suspend fun getRandomFact(): Fact

    suspend fun insertFavoriteFact(fact: Fact)

    suspend fun deleteFavoriteFact(fact: Fact)
}

class CashingFactRepository(private val factDao: FactDao, private val factsApiService: FactsApiService, context: Context) : FactRepository {

    override fun getFavoriteFacts(): Flow<List<Fact>> = factDao.getAll().map { it.asDomainFacts() }

    override suspend fun getRandomFact(): Fact = factsApiService.getFacts().facts.toFacts().first()

    override suspend fun insertFavoriteFact(fact: Fact) = factDao.insert(fact.asDbFact())

    override suspend fun deleteFavoriteFact(fact: Fact) = factDao.delete(fact.asDbFact())
}
