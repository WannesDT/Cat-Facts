package com.example.catfacts.fake

import com.example.catfacts.data.database.asDbFact
import com.example.catfacts.data.toFact
import com.example.catfacts.network.facts.ApiFacts

/**
 * A fake data source for generating predefined [Fact] objects and a list of favorite facts.
 */
object FakeDataSource {
    /**
     * Predefined [Fact] object with content "fact 1".
     */
    val fact1 = "fact 1".toFact()

    /**
     * Predefined [Fact] object with content "fact 2".
     */
    val fact2 = "fact 2".toFact()

    /**
     * Predefined list of favorite facts wrapped in an [DbFacts] object.
     */
    val favoFacts = listOf(fact1.asDbFact(), fact2.asDbFact())
}
