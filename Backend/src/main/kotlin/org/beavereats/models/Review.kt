package org.beavereats.models

import kotlinx.serialization.Serializable
import org.beavereats.storage.database

@Serializable
data class Review(
    var userId: String?,
    val locationId: String,
    var rating: Int,
    var review: String?
)

val reviews = database.getCollection<Review>("reviews")
