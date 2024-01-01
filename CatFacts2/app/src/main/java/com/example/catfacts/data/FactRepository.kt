package com.example.catfacts.data

import android.content.Context
import com.example.catfacts.data.database.FactDao
import com.example.catfacts.data.database.asDbFact
import com.example.catfacts.data.database.asDomainFacts
import com.example.catfacts.network.facts.FactsApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


/**
 * Interface defining operations for managing facts, such as retrieving favorite facts,
 * getting a random fact, inserting a favorite fact, and deleting a favorite fact.
 */
interface FactRepository {
    /**
     * Retrieves a [Flow] of all favorite facts.
     *
     * @return A [Flow] emitting a list of [Fact] instances representing favorite facts.
     */
    fun getFavoriteFacts(): Flow<List<Fact>>

    /**
     * Retrieves a random fact from the API.
     *
     * @return A randomly selected [Fact] instance.
     */
    suspend fun getRandomFact(): Fact

    /**
     * Inserts a fact as a favorite into the data source.
     *
     * @param fact The [Fact] instance to be inserted as a favorite.
     */
    suspend fun insertFavoriteFact(fact: Fact)

    /**
     * Deletes a favorite fact from the data source.
     *
     * @param fact The [Fact] instance to be deleted from favorites.
     */
    suspend fun deleteFavoriteFact(fact: Fact)
}

/**
 * Implementation of [FactRepository] that caches facts in a local database.
 *
 * @param factDao The DAO for accessing the local database.
 * @param factsApiService The service for fetching facts from the network.
 * @param context The application context.
 */
class CashingFactRepository(private val factDao: FactDao, private val factsApiService: FactsApiService, context: Context) : FactRepository {

    override fun getFavoriteFacts(): Flow<List<Fact>> = factDao.getAll().map { it.asDomainFacts() }

    override suspend fun getRandomFact(): Fact = factsApiService.getFacts().facts.toFacts().first()

    override suspend fun insertFavoriteFact(fact: Fact) = factDao.insert(fact.asDbFact())

    override suspend fun deleteFavoriteFact(fact: Fact) = factDao.delete(fact.asDbFact())
}
