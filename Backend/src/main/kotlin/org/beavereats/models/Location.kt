package org.beavereats.models

import com.mongodb.client.model.Filters.eq
import kotlinx.coroutines.flow.toList
import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val displayName: String,
    val id: String,
    val group: LocationGroups,
    val description: String,
    val opens: Int,
    val closes: Int,
    val lat: Double,
    val lng: Double,
    val images: List<String> = listOf("https://via.placeholder.com/150"),
    var rating: Double = 5.0
) {
    suspend fun updateRating() {
        reviews.find(eq(Location::id.name, this.id)).toList().let {
            if (it.isEmpty()) {
                this.rating = 5.0
                return
            }
            this.rating = it.map { it.rating }.average()
        }
    }
}

enum class LocationGroups(val displayName: String) {
    McNary("McNary"),
    Arnold("Arnold"),
    West("West"),
    MU("Memorial Union"),
    Other("Cafes & Markets")
}

val locations = listOf(
    Location("Trader Bing's", "trader-bings", LocationGroups.Other, "description", 7, 15, 44.56501149450507, -123.2824812707443),
    Location("Dixon Cafe", "dixon-cafe", LocationGroups.Other, "description", 7, 15, 44.56312690366964, -123.27906269265074),
    Location("Cascadia Market", "cascadia-market", LocationGroups.Other, "description", 7, 15, 44.559984344387885, -123.27639083916388),
    Location("Cascadia Cafe", "cascadia-cafe", LocationGroups.Other, "description", 7, 15, 44.55995234879312, -123.27622677645999),
    Location("Cascadia Deli", "cascadia-deli", LocationGroups.Other, "description", 7, 15, 44.55995234879312, -123.27622677645999),
    Location("e.Cafe", "ecafe", LocationGroups.Other, "description", 7, 15, 44.56696582550153, -123.2789105989436),
    Location("Ava's Cafe", "avas-cafe", LocationGroups.Other, "description", 7, 15, 44.566415042430876, -123.28328840022844),
    Location("Coffee Corral", "coffee-corral", LocationGroups.Other, "description", 7, 15, 44.5606140138659, -123.28575359406544),
    Location("The Dam", "the-dam", LocationGroups.Other, "description", 7, 15, 44.56570378738891, -123.28118963312197),
    Location("Java II", "java-2", LocationGroups.Other, "description", 7, 15, 44.565268524431985, -123.27553471386126),
    Location("Bing's Cafe", "bings-cafe", LocationGroups.Other, "description", 7, 15, 44.56409146359079, -123.28016777479642),

    Location("West Side Grill", "west-side-grill", LocationGroups.West, "description", 7, 15, 44.56404980178773, -123.28391900113377),
    Location("Clubhouse Deli", "clubhouse-deli", LocationGroups.West, "description", 7, 15, 44.56398482841707, -123.2837614210447),
    Location("Cooper's Creek", "coopers-creek", LocationGroups.West, "description", 7, 15, 44.56404167997718, -123.28322900198417),
    Location("EBGBs", "ebgbs", LocationGroups.West, "description", 7, 15, 44.56393708326589, -123.2839125294408),
    Location("Ring of Fire", "ring-of-fire", LocationGroups.West, "description", 7, 15, 44.564045531897754, -123.28344716588074),
    Location("Tomassito's Italian Cafe", "tomassitos-italian-cafe", LocationGroups.West, "description", 7, 15, 44.56395237100268, -123.28332445494368),
    Location("Serrano", "serrano", LocationGroups.West, "description", 7, 15, 44.56394950421886, -123.28321247260088),

    Location("East Side Eats", "east-side-eats", LocationGroups.McNary, "description", 7, 15, 44.56396266015183, -123.27223883359622),
    Location("Calabaloo's", "calabaloos", LocationGroups.McNary, "description", 7, 15, 44.56405274971014, -123.27210990844247),
    Location("The Main Squeeze", "the-main-squeeze", LocationGroups.McNary, "description", 7, 15, 44.56419163104882, -123.2723429315108),
    Location("RainTree Coffee","raintree-coffee", LocationGroups.McNary, "description", 7, 15, 44.56403026739063, -123.27219564748334),
    Location("Five Four One", "five-four-one", LocationGroups.McNary, "description", 7, 15, 44.56415218040551, -123.27199017018366),
    Location("La Calle", "la-calle", LocationGroups.McNary, "description", 7, 15, 44.56405925227253, -123.2724240401381),

    Location("Bites", "bites", LocationGroups.MU, "description", 7, 15, 44.56494644817311, -123.27846244894907),
    Location("JavaStop", "javastop", LocationGroups.MU, "description", 7, 15, 44.56495739528206, -123.27876209552703),
    Location("Off the Quad", "off-the-quad", LocationGroups.MU, "description", 7, 15, 44.56498484299334, -123.27900444465249),
    Location("Rocket Burger", "rocket-burger", LocationGroups.MU, "description", 7, 15, 44.56500580148071, -123.27958250752957),
    Location("Panda Express", "panda-express", LocationGroups.MU, "description", 7, 15, 44.56484145993573, -123.27941419816489),
    Location("North Porch Cafe", "north-porch-cafe", LocationGroups.MU, "description", 7, 15, 44.56512854182737, -123.27817586645494),

    Location("Global Fare", "global-fare", LocationGroups.Arnold, "description", 7, 15, 44.5607682191321, -123.27757265633848),
    Location("Nori", "nori", LocationGroups.Arnold, "description", 7, 15, 44.56044300825791, -123.27757323586768),
    Location("The Grill", "grill", LocationGroups.Arnold, "description", 7, 15, 44.56077926029649, -123.2774755766198),
    Location("Arnold Deli", "arnold-deli", LocationGroups.Arnold, "description", 7, 15, 44.56044178256524, -123.27751955755423),
    Location("Arnold Pizza", "arnold-pizza", LocationGroups.Arnold, "description", 7, 15, 44.560502046094804, -123.27769886651917),
)
