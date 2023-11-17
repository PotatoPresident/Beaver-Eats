package org.beavereats.models

import kotlinx.serialization.Serializable
import java.time.DayOfWeek

@Serializable
data class Location(
    val name: String,
    val parent: LocationGroups,
//    val menu: List<String>,
//    val hours: Map<DayOfWeek, Int>
)

enum class LocationGroups {
    McNary,
    Arnold,
    West,
    MU,
    Other
}

val locations = listOf(
    Location("Trader Bing's", LocationGroups.Other),
    Location("Dixon Cafe", LocationGroups.Other),
    Location("Cascadia Market", LocationGroups.Other),
    Location("Cascadia Cafe", LocationGroups.Other),
    Location("Cascadia Deli", LocationGroups.Other),
    Location("e.Cafe", LocationGroups.Other),
    Location("Ava's Cafe", LocationGroups.Other),
    Location("Coffee Corral", LocationGroups.Other),

    Location("West Side Grill", LocationGroups.West),
    Location("Clubhouse Deli", LocationGroups.West),
    Location("Cooper's Creek", LocationGroups.West),
    Location("EBGBs", LocationGroups.West),
    Location("Ring of Fire", LocationGroups.West),
    Location("Tomassito's Italian Cafe", LocationGroups.West),
    Location("Serrano", LocationGroups.West),
)
