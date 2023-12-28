package com.example.catfacts.fake

import com.example.catfacts.data.toFact
import com.example.catfacts.network.facts.ApiFacts

object FakeDataSource {
    val fact1 = "fact 1".toFact()
    val fact2 = "fact 2".toFact()

    val favoFacts = ApiFacts(listOf(fact1.content, fact2.content))
}
