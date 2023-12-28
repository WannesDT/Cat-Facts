package com.example.catfacts.data

data class Fact(val content: String, val isFavorite: Boolean = false)

fun String.toFact(): Fact = Fact(content = this)

fun List<String>.toFacts(): List<Fact> = map { it.toFact() }
