package com.example.catfacts.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * An interface that describes all connection to the Fact table
 */
@Dao
interface FactDao {
    /**
     * Select's all DbFact's from the table DbFact
     * @return A list of DbFact's in a flow
     */
    @Query("SELECT * FROM DbFact")
    fun getAll(): Flow<List<DbFact>>

    /**
     * Inserts an DbFact
     * @param dbFact the DbFact that needs to be inserted
     */
    @Insert
    suspend fun insert(vararg dbFact: DbFact)

    /**
     * Delete's an DbFact
     * @param dbFact the DbFact that needs to be deleted
     */
    @Delete
    suspend fun delete(dbFact: DbFact)
}
