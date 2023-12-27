package com.example.catfacts.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FactDao {
    @Query("SELECT * FROM DbFact")
    fun getAll(): Flow<List<DbFact>>

    @Insert
    suspend fun insert(vararg dbFact: DbFact)

    @Delete
    suspend fun delete(dbFact: DbFact)
}
