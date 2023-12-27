package com.example.catfacts.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DbFact::class], version = 2)
abstract class CatsDatabase : RoomDatabase() {
    abstract fun factDao(): FactDao
}
