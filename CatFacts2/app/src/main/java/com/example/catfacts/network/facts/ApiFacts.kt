package com.example.catfacts.network.facts

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Serializable data class representing facts retrieved from the API.
 *
 * @param facts The list of facts obtained from the API.
 */
@Serializable
data class ApiFacts(
    @SerialName("data") val facts: List<String>,
)
