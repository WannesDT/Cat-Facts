package com.example.catfacts.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * This class creates a Database for catfacts
 */
@Database(entities = [DbFact::class], version = 2)
abstract class CatsDatabase : RoomDatabase() {
    /**
     * This return the Data acces object for a fact
     * @return Data acces object for fact
     */
    abstract fun factDao(): FactDao
}
