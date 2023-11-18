package org.beavereats.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val id: String = "DEFAULT",
    val name: String,
    @SerialName("given_name") val givenName: String,
    @SerialName("family_name") val familyName: String? = null,
    val picture: String,
    val locale: String
)