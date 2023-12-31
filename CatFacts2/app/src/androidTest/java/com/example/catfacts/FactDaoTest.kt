package com.example.catfacts

import android.content.Context
import androidx.room.Room.inMemoryDatabaseBuilder
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.catfacts.data.Fact
import com.example.catfacts.data.database.CatsDatabase
import com.example.catfacts.data.database.FactDao
import com.example.catfacts.data.database.asDbFact
import com.example.catfacts.data.database.asDomainFact
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class FactDaoTest {
    private lateinit var factDao: FactDao
    private lateinit var catsDatabase: CatsDatabase

    private var fact1 = Fact("Cat Fact 1")
    private var fact2 = Fact("Cat Fact 2")

    /**
     * Utility function to add one fact to the database.
     */
    private suspend fun addOnefactToDb() {
        factDao.insert(fact1.asDbFact())
    }

    /**
     * Utility function to add two facts to the database.
     */
    private suspend fun addTwofactsToDb() {
        factDao.insert(fact1.asDbFact())
        factDao.insert(fact2.asDbFact())
    }

    /**
     * Utility function to remove the first fact from the database.
     */
    private suspend fun removeFirstFactFromDb() {
        factDao.delete(fact1.asDbFact())
    }

    /**
     * Set up the in-memory database for testing.
     */
    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        catsDatabase = inMemoryDatabaseBuilder(context, CatsDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        factDao = catsDatabase.factDao()
    }

    /**
     * Close the in-memory database after testing.
     */
    @After
    @Throws(IOException::class)
    fun closeDb() {
        catsDatabase.close()
    }

    /**
     * Test to verify if inserting one fact into the database works correctly.
     */
    @Test
    @Throws(Exception::class)
    fun daoInsert_insertfactIntoDB() = runBlocking {
        addOnefactToDb()
        val allItems = factDao.getAll().first()
        assertEquals(allItems[0].asDomainFact(), fact1)
    }

    /**
     * Test to verify if retrieving all facts from the database works correctly.
     */
    @Test
    @Throws(Exception::class)
    fun daoGetAllfacts_returnsAllfactsFromDB() = runBlocking {
        addTwofactsToDb()
        val allItems = factDao.getAll().first()
        assertEquals(allItems[0].asDomainFact(), fact1)
        assertEquals(allItems[1].asDomainFact(), fact2)
    }

    /**
     * Test to verify if removing one fact from the database works correctly.
     */
    @Test
    @Throws(Exception::class)
    fun daoRemoveFact_returnsAllfactsFromDB() = runBlocking {
        addTwofactsToDb()
        removeFirstFactFromDb()

        val allItems = factDao.getAll().first()

        assertEquals(allItems[0].asDomainFact(), fact2)
    }
}
