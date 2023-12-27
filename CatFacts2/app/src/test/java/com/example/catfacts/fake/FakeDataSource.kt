package com.example.catfacts.fake

import com.example.catfacts.network.facts.ApiFacts

object FakeDataSource {
    const val fact1 = "fact 1"
    const val fact2 = "fact 2"

    val favoFacts = ApiFacts(listOf(fact1, fact2))
}
