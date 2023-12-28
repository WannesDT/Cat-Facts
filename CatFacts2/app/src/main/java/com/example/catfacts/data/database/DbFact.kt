package com.example.catfacts.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.catfacts.data.Fact

/**
 * the data class that is Used for a fact
 *
 * @param content the content of of this fact
 */
@Entity
data class DbFact(
    @PrimaryKey
    @ColumnInfo(name = "content")
    val content: String,
)

/**
 * Converts a domain fact into an DbFact
 * @return DbFact
 */
fun Fact.asDbFact() = DbFact(content = content)

/**
 * Converts a DbFact into an domain Fact
 * @return Fact
 */
fun DbFact.asDomainFact() = Fact(content = content)

/**
 * Converts a list of DbFact's into an list of domain fact's
 * @return List<Fact>
 */
fun List<DbFact>.asDomainFacts() = map { it.asDomainFact() }

/**
 * Converts a list of domain fact's into an list of DbFact's
 * @return List<DbFact>
 */
fun List<Fact>.asDbFacts() = map { it.asDbFact() }
