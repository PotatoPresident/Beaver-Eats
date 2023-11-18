package org.beavereats.models

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val id: String = "DEFAULT",
    val name: String,
    val email: String,
    val picture: String,
)