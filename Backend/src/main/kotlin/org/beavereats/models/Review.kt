package org.beavereats.models

import kotlinx.serialization.Serializable
import org.beavereats.storage.database

@Serializable
data class Review(
    val userId: String,
    val rating: Int,
    val review: String?
)

val reviews = database.getCollection<Review>("reviews")
