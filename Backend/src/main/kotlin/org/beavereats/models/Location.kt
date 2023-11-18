package org.beavereats.models

import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val displayName: String,
    val id: String,
    val group: LocationGroups,
//    val menu: List<String>,
//    val hours: Map<DayOfWeek, Int>
)

enum class LocationGroups(val displayName: String) {
    McNary("McNary"),
    Arnold("Arnold"),
    West("West"),
    MU("Memorial Union"),
    Other("Cafes & Markets")
}

val locations = listOf(
    Location("Trader Bing's", "trader-bings", LocationGroups.Other),
    Location("Dixon Cafe", "dixon-cafe", LocationGroups.Other),
    Location("Cascadia Market", "cascadia-market", LocationGroups.Other),
    Location("Cascadia Cafe", "cascadia-cafe", LocationGroups.Other),
    Location("Cascadia Deli", "cascadia-deli", LocationGroups.Other),
    Location("e.Cafe", "ecafe", LocationGroups.Other),
    Location("Ava's Cafe", "avas-cafe", LocationGroups.Other),
    Location("Coffee Corral", "coffee-corral", LocationGroups.Other),
    Location("The Dam", "the-dam", LocationGroups.Other),
    Location("Java II", "java-2", LocationGroups.Other),
    Location("Bing's Cafe", "bings-cafe", LocationGroups.Other),

    Location("West Side Grill", "west-side-grill", LocationGroups.West),
    Location("Clubhouse Deli", "clubhouse-deli", LocationGroups.West),
    Location("Cooper's Creek", "coopers-creek", LocationGroups.West),
    Location("EBGBs", "ebgbs", LocationGroups.West),
    Location("Ring of Fire", "ring-of-fire", LocationGroups.West),
    Location("Tomassito's Italian Cafe", "tomassitos-italian-cafe", LocationGroups.West),
    Location("Serrano", "serrano", LocationGroups.West),

    Location("East Side Eats", "east-side-eats", LocationGroups.McNary),
    Location("Calabaloo's", "calabaloos", LocationGroups.McNary),
    Location("The Main Squeeze", "the-main-squeeze", LocationGroups.McNary),
    Location("RainTree Coffee","raintree-coffe", LocationGroups.McNary),
    Location("Five Four One", "five-four-one", LocationGroups.McNary),
    Location("La Calle", "la-calle", LocationGroups.McNary),

    Location("Bites", "bites", LocationGroups.MU),
    Location("JavaStop", "javastop", LocationGroups.MU),
    Location("Off the Quad", "off-the-quad", LocationGroups.MU),
    Location("Rocket Burger", "rocket-burger", LocationGroups.MU),
    Location("Panda Express", "panda-express", LocationGroups.MU),
    Location("North Porch Cafe", "north-porch-cafe", LocationGroups.MU),

    Location("Global Fare", "global-fare", LocationGroups.Arnold),
    Location("Nori", "nori", LocationGroups.Arnold),
    Location("The Grill", "grill", LocationGroups.Arnold),
    Location("Arnold Deli", "arnold-deli", LocationGroups.Arnold),
    Location("Arnold Pizza", "arnold-pizza", LocationGroups.Arnold),
)
