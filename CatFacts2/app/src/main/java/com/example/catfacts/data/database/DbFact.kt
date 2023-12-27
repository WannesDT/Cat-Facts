package com.example.catfacts.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.catfacts.data.Fact

@Entity
data class DbFact(
    @PrimaryKey
    @ColumnInfo(name = "content")
    val content: String,
)
fun Fact.asDbFact() = DbFact(content = content)

fun DbFact.asDomainFact() = Fact(content = content)

fun List<DbFact>.asDomainFacts() = map { it.asDomainFact() }
fun List<Fact>.asDbFacts() = map { it.asDbFact() }
