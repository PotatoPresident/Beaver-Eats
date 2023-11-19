package org.beavereats.models

import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val displayName: String,
    val id: String,
    val group: LocationGroups,
    val description: String,
    val opens: Int,
    val closes: Int,
    val images: List<String> = listOf("https://via.placeholder.com/150"),
    var rating: Double = 5.0,
)

enum class LocationGroups(val displayName: String) {
    McNary("McNary"),
    Arnold("Arnold"),
    West("West"),
    MU("Memorial Union"),
    Other("Cafes & Markets")
}

val locations = listOf(
    Location("Trader Bing's", "trader-bings", LocationGroups.Other, "description", 7, 15),
    Location("Dixon Cafe", "dixon-cafe", LocationGroups.Other, "description", 7, 15),
    Location("Cascadia Market", "cascadia-market", LocationGroups.Other, "description", 7, 15),
    Location("Cascadia Cafe", "cascadia-cafe", LocationGroups.Other, "description", 7, 15),
    Location("Cascadia Deli", "cascadia-deli", LocationGroups.Other, "description", 7, 15),
    Location("e.Cafe", "ecafe", LocationGroups.Other, "description", 7, 15),
    Location("Ava's Cafe", "avas-cafe", LocationGroups.Other, "description", 7, 15),
    Location("Coffee Corral", "coffee-corral", LocationGroups.Other, "description", 7, 15),
    Location("The Dam", "the-dam", LocationGroups.Other, "description", 7, 15),
    Location("Java II", "java-2", LocationGroups.Other, "description", 7, 15),
    Location("Bing's Cafe", "bings-cafe", LocationGroups.Other, "description", 7, 15),

    Location("West Side Grill", "west-side-grill", LocationGroups.West, "description", 7, 15),
    Location("Clubhouse Deli", "clubhouse-deli", LocationGroups.West, "description", 7, 15),
    Location("Cooper's Creek", "coopers-creek", LocationGroups.West, "description", 7, 15),
    Location("EBGBs", "ebgbs", LocationGroups.West, "description", 7, 15),
    Location("Ring of Fire", "ring-of-fire", LocationGroups.West, "description", 7, 15),
    Location("Tomassito's Italian Cafe", "tomassitos-italian-cafe", LocationGroups.West, "description", 7, 15),
    Location("Serrano", "serrano", LocationGroups.West, "description", 7, 15),

    Location("East Side Eats", "east-side-eats", LocationGroups.McNary, "description", 7, 15),
    Location("Calabaloo's", "calabaloos", LocationGroups.McNary, "description", 7, 15),
    Location("The Main Squeeze", "the-main-squeeze", LocationGroups.McNary, "description", 7, 15),
    Location("RainTree Coffee","raintree-coffee", LocationGroups.McNary, "description", 7, 15),
    Location("Five Four One", "five-four-one", LocationGroups.McNary, "description", 7, 15),
    Location("La Calle", "la-calle", LocationGroups.McNary, "description", 7, 15),

    Location("Bites", "bites", LocationGroups.MU, "description", 7, 15),
    Location("JavaStop", "javastop", LocationGroups.MU, "description", 7, 15),
    Location("Off the Quad", "off-the-quad", LocationGroups.MU, "description", 7, 15),
    Location("Rocket Burger", "rocket-burger", LocationGroups.MU, "description", 7, 15),
    Location("Panda Express", "panda-express", LocationGroups.MU, "description", 7, 15),
    Location("North Porch Cafe", "north-porch-cafe", LocationGroups.MU, "description", 7, 15),

    Location("Global Fare", "global-fare", LocationGroups.Arnold, "description", 7, 15),
    Location("Nori", "nori", LocationGroups.Arnold, "description", 7, 15),
    Location("The Grill", "grill", LocationGroups.Arnold, "description", 7, 15),
    Location("Arnold Deli", "arnold-deli", LocationGroups.Arnold, "description", 7, 15),
    Location("Arnold Pizza", "arnold-pizza", LocationGroups.Arnold, "description", 7, 15),
)
