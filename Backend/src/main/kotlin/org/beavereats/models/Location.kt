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
    val images: List<String>,
    var rating: Double = 5.0
) {
    suspend fun updateRating() {
        reviews.find(eq(Review::locationId.name, this.id)).toList().let {
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
    Location("Trader Bing's", "trader-bings", LocationGroups.Other, "description", 7, 18, 44.56501149450507, -123.2824812707443, listOf("/api/images/traderbings.jpeg")),
    Location("Dixon Cafe", "dixon-cafe", LocationGroups.Other, "description", 10, 10, 44.56312690366964, -123.27906269265074, listOf("/api/images/dixoncafe.jpeg")),
    Location("Cascadia Market", "cascadia-market", LocationGroups.Other, "description", 9, 22, 44.559984344387885, -123.27639083916388, listOf("/api/images/cascadiamarket.jpeg")),
    Location("Cascadia Cafe", "cascadia-cafe", LocationGroups.Other, "description", 7, 15, 44.55995234879312, -123.27622677645999, listOf("/api/images/cascadiacafe.png")),
    Location("Cascadia Deli", "cascadia-deli", LocationGroups.Other, "description", 11, 21, 44.55995234879312, -123.27622677645999, listOf("/api/images/cascadiadeli.jpeg")),
    Location("e.Cafe", "ecafe", LocationGroups.Other, "description", 7, 18, 44.56696582550153, -123.2789105989436, listOf("/api/images/e.cafe.jpeg")),
    Location("Ava's Cafe", "avas-cafe", LocationGroups.Other, "description", 7, 17, 44.566415042430876, -123.28328840022844, listOf("/api/images/avascafe.jpeg")),
    Location("Coffee Corral", "coffee-corral", LocationGroups.Other, "description", 7, 17, 44.5606140138659, -123.28575359406544, listOf("/api/images/coffeecorral.jpeg")),
    Location("The Dam", "the-dam", LocationGroups.Other, "description", 7, 13, 44.56570378738891, -123.28118963312197, listOf("/api/images/thedam.jpeg")),
    Location("Java II", "java-2", LocationGroups.Other, "description", 7, 22, 44.565268524431985, -123.27553471386126, listOf("/api/images/javaii.jpeg")),
    Location("Bing's Cafe", "bings-cafe", LocationGroups.Other, "description", 7, 15, 44.56409146359079, -123.28016777479642, listOf("/api/images/bings.png")),

    Location("West Side Grill", "west-side-grill", LocationGroups.West, "description", 16, 19, 44.56404980178773, -123.28391900113377, listOf("/api/images/westsidegrill.png")),
    Location("Clubhouse Deli", "clubhouse-deli", LocationGroups.West, "description", 7, 20, 44.56398482841707, -123.2837614210447, listOf("/api/images/clubhousedeli.png")),
    Location("Cooper's Creek", "coopers-creek", LocationGroups.West, "description", 11, 15, 44.56404167997718, -123.28322900198417, listOf("/api/images/cooperscreek.png")),
    Location("EBGBs", "ebgbs", LocationGroups.West, "description", 7, 20, 44.56393708326589, -123.2839125294408, listOf("/api/images/ebgbs.jpeg")),
    Location("Ring of Fire", "ring-of-fire", LocationGroups.West, "description", 11, 19, 44.564045531897754, -123.28344716588074, listOf("/api/images/ringoffire.png")),
    Location("Tomassito's Italian Cafe", "tomassitos-italian-cafe", LocationGroups.West, "description", 12, 15, 44.56395237100268, -123.28332445494368, listOf("/api/images/tomassitos.png")),
    Location("Serrano", "serrano", LocationGroups.West, "description", 7, 20, 44.56394950421886, -123.28321247260088, listOf("/api/images/serrano.jpeg")),

    Location("East Side Eats", "east-side-eats", LocationGroups.McNary, "description", 7,  20, 44.56396266015183, -123.27223883359622, listOf("/api/images/eastsideeats.jpeg")),
    Location("Calabaloo's", "calabaloos", LocationGroups.McNary, "description", 11, 20, 44.56405274971014, -123.27210990844247, listOf("/api/images/calabaloos.png")),
    Location("The Main Squeeze", "the-main-squeeze", LocationGroups.McNary, "description", 9, 23, 44.56419163104882, -123.2723429315108, listOf("/api/images/themainsqueeze.png")),
    Location("RainTree Coffee","raintree-coffee", LocationGroups.McNary, "description", 7, 14, 44.56403026739063, -123.27219564748334, listOf("/api/images/raintreecoffeeco.jpeg")),
    Location("Five Four One", "five-four-one", LocationGroups.McNary, "description", 11, 19, 44.56415218040551, -123.27199017018366, listOf("/api/images/fivefourone.png")),
    Location("La Calle", "la-calle", LocationGroups.McNary, "description", 19, 23, 44.56405925227253, -123.2724240401381, listOf("/api/images/lacalle.png")),

    Location("Bites", "bites", LocationGroups.MU, "description", 7, 19, 44.56494644817311, -123.27846244894907, listOf("/api/images/bites.jpeg")),
    Location("JavaStop", "javastop", LocationGroups.MU, "description", 7, 19, 44.56495739528206, -123.27876209552703, listOf("/api/images/javastop.jpeg")),
    Location("Off the Quad", "off-the-quad", LocationGroups.MU, "description", 9, 15, 44.56498484299334, -123.27900444465249, listOf("/api/images/offthequad.png")),
    Location("Rocket Burger", "rocket-burger", LocationGroups.MU, "description", 10, 20, 44.56500580148071, -123.27958250752957, listOf("/api/images/rocketburger.webp")),
    Location("Panda Express", "panda-express", LocationGroups.MU, "description", 10, 20, 44.56484145993573, -123.27941419816489, listOf("/api/images/panda.png")),
    Location("North Porch Cafe", "north-porch-cafe", LocationGroups.MU, "description", 10, 15, 44.56512854182737, -123.27817586645494, listOf("/api/images/norchporchcafe.jpeg")),

    Location("Global Fare", "global-fare", LocationGroups.Arnold, "description", 7, 20, 44.5607682191321, -123.27757265633848, listOf("/api/images/globalfare.png")),
    Location("Nori", "nori", LocationGroups.Arnold, "description", 7, 20, 44.56044300825791, -123.27757323586768, listOf("/api/images/nori.jpeg")),
    Location("The Grill", "grill", LocationGroups.Arnold, "description", 11, 19, 44.56077926029649, -123.2774755766198, listOf("/api/images/grill.png")),
    Location("Arnold Deli", "arnold-deli", LocationGroups.Arnold, "description", 10, 17, 44.56044178256524, -123.27751955755423, listOf("/api/images/deli.png")),
    Location("Arnold Pizza", "arnold-pizza", LocationGroups.Arnold, "description", 11, 9, 44.560502046094804, -123.27769886651917, listOf("/api/images/pizzeria.png")),
)
