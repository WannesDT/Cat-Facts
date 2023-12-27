package com.example.catfacts.network.facts

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiFacts(
    @SerialName("data") val facts: List<String>,
)
