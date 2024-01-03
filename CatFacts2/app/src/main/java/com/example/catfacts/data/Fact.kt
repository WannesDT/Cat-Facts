package com.example.catfacts.data

import java.util.UUID

/**
 * Data class representing a fact with content and a favorite status.
 *
 * @param content The content of the fact.
 * @param isFavorite Indicates whether the fact is marked as a favorite (default is false).
 */
data class Fact(val content: String, val isFavorite: Boolean = false, val UID: String = generateId()) {
    companion object {
        private fun generateId(): String {
            return UUID.randomUUID().toString()
        }
    }
}

/**
 * Extension function to convert a [String] into a [Fact].
 *
 * @receiver The string content to be converted into a [Fact].
 * @return A new [Fact] instance with the provided content.
 */
fun String.toFact(): Fact = Fact(content = this)

/**
 * Extension function to convert a list of [String]s into a list of [Fact]s.
 *
 * @receiver The list of strings to be converted into a list of [Fact]s.
 * @return A new list of [Fact] instances with the provided contents.
 */
fun List<String>.toFacts(): List<Fact> = map { it.toFact() }
