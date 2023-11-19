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
    Location("Trader Bing's", "trader-bings", LocationGroups.Other, "Featuring their white cheddar mac & cheese, Trader Bing’s Café is serving up a variety of breakfast and lunch options, including sandwiches, salads and wraps, as well as your favorite Portland Roasting espresso, coffee and tea drinks. Conveniently located in the Austin Hall lobby.", 7, 18, 44.56501149450507, -123.2824812707443, listOf("/api/images/traderbings.jpeg")),
    Location("Dixon Cafe", "dixon-cafe", LocationGroups.Other, "Dixon Café is here to fuel your day with a variety of nutritious options, including real fruit smoothies, tasty wraps, smoothie bowls, quinoa bowls, breakfast options and grab-and-go items sure to satisfy your hunger.  ", 10, 10, 44.56312690366964, -123.27906269265074, listOf("/api/images/dixoncafe.jpeg")),
    Location("Cascadia Market", "cascadia-market", LocationGroups.Other, "Shop for snacks and grocery items without ever leaving campus. Cascadia Market carries fresh produce by the pound, dairy items, vegan items, international cuisine, gluten-free items, frozen food, a bread selection.", 9, 22, 44.559984344387885, -123.27639083916388, listOf("/api/images/cascadiamarket.jpeg")),
    Location("Cascadia Cafe", "cascadia-cafe", LocationGroups.Other, "Cascadia Cafe serves local Pastega Coffee Roasters coffee and espresso drinks, plus teas, matcha, chai and seasonal specialty drinks. Pair your beverage with breakfast sandwiches, sweet crepes, overnight oats, or a delicious pastry from the campus bakery.  Located next to Cascadia Market in the ILLC.", 7, 15, 44.55995234879312, -123.27622677645999, listOf("/api/images/cascadiacafe.png")),
    Location("Cascadia Deli", "cascadia-deli", LocationGroups.Other, "Choose from made-to-order sandwiches, rice bowls, grab-n-go items, and more at Cascadia Deli, located inside Cascadia Market. Try new specialty sandwiches weekly! Vegan and vegetarian options are available.", 11, 21, 44.55995234879312, -123.27622677645999, listOf("/api/images/cascadiadeli.jpeg")),
    Location("e.Cafe", "ecafe", LocationGroups.Other, "Located in the Kelley Engineering Center main lobby, the e.Cafe menu includes breakfast, toasted sandwiches, gyros, smoothies, mac & cheese and pastries from local bakeries.  Portland Coffee Roasters is featured in the hand-crafted beverages, beside other creations with tea, matcha and chai.", 7, 18, 44.56696582550153, -123.2789105989436, listOf("/api/images/e.cafe.jpeg")),
    Location("Ava's Cafe", "avas-cafe", LocationGroups.Other, "Ava’s Café, on the main floor of Linus Pauling Science Center, offers breakfast sandwiches, quesadillas, pasta alfredo, signature grilled sandwiches, smoothies, blended drinks, assorted pastries and a selection of grab-and-go items.  Proudly serving Portland Coffee Roasters coffee.", 7, 17, 44.566415042430876, -123.28328840022844, listOf("/api/images/avascafe.jpeg")),
    Location("Coffee Corral", "coffee-corral", LocationGroups.Other, "Coffee Corral serves an all-day breakfast menu, custom bagel and wrap sandwiches, salads, pastries, smoothies and espresso drinks that feature Portland Coffee Roasters.", 7, 17, 44.5606140138659, -123.28575359406544, listOf("/api/images/coffeecorral.jpeg")),
    Location("The Dam", "the-dam", LocationGroups.Other, "Offering a full line of Portland Coffee Roaster espresso drinks, refrigerated grab-and-go items such as sandwiches and salads, as well as snacks, pastries, and bottled beverages to get you through your next class.  The Dam also includes options for special diet needs.  Located on the ground floor of the bustling Learning Innovation Center.", 7, 13, 44.56570378738891, -123.28118963312197, listOf("/api/images/thedam.jpeg")),
    Location("Java II", "java-2", LocationGroups.Other, "Java II prepares hand crafted beverages with Portland Coffee Roasters and offers a variety of delicious sandwiches, salads and snacks.  Open later than most other locations on campus, find Java II by the east entry of the Valley Library for all of your study break needs!", 7, 22, 44.565268524431985, -123.27553471386126, listOf("/api/images/javaii.jpeg")),
    Location("Bing's Cafe", "bings-cafe", LocationGroups.Other, "Proudly brewing Starbucks coffee, this is the place to get hand-crafted espresso drinks made with Starbucks coffee and Teavana tea. Enjoy all-day breakfast sandwiches, pastries from our Bakeshop, as well as snacks and bottled beverages.", 7, 15, 44.56409146359079, -123.28016777479642, listOf("/api/images/bings.png")),

    Location("West Side Grill", "west-side-grill", LocationGroups.West, " West Side Grill offers house-made breakfast favorites in the morning like eggs-your-way, pancakes and biscuits & gravy. Choose from a selection of breakfast items and artisan sandwiches at lunch, and build-your-own burger for dinner.", 16, 19, 44.56404980178773, -123.28391900113377, listOf("/api/images/westsidegrill.png")),
    Location("Clubhouse Deli", "clubhouse-deli", LocationGroups.West, "Acai bowls, salad bar, soups and custom-made sandwiches", 7, 20, 44.56398482841707, -123.2837614210447, listOf("/api/images/clubhousedeli.png")),
    Location("Cooper's Creek", "coopers-creek", LocationGroups.West, "Breakfast items, grain bowls, salads and classic American fare.", 11, 15, 44.56404167997718, -123.28322900198417, listOf("/api/images/cooperscreek.png")),
    Location("EBGBs", "ebgbs", LocationGroups.West, "Organically grown, fair-trade coffee from Cafe Mam; house-made pastries; ice cream, smoothies and snacks.", 7, 20, 44.56393708326589, -123.2839125294408, listOf("/api/images/ebgbs.jpeg")),
    Location("Ring of Fire", "ring-of-fire", LocationGroups.West, "Ring of Fire features a rotating menu of dishes inspired by the cuisine of Asia, India, and the Pacific Islands. Chefs source ingredients from the Pacific Northwest but draw on a variety of global culinary traditions to create fresh, flavorful takes on traditional favorites.", 11, 19, 44.564045531897754, -123.28344716588074, listOf("/api/images/ringoffire.png")),
    Location("Tomassito's Italian Cafe", "tomassitos-italian-cafe", LocationGroups.West, "Pizza by the slice, salads, and pasta.", 12, 15, 44.56395237100268, -123.28332445494368, listOf("/api/images/tomassitos.png")),
    Location("Serrano", "serrano", LocationGroups.West, " Burritos, bowls and nachos made-to-order.", 7, 20, 44.56394950421886, -123.28321247260088, listOf("/api/images/serrano.jpeg")),

    Location("East Side Eats", "east-side-eats", LocationGroups.McNary, " Breakfast items, yogurt bar, stir-fry bar, salad bar and daily specials.", 7,  20, 44.56396266015183, -123.27223883359622, listOf("/api/images/eastsideeats.jpeg")),
    Location("Calabaloo's", "calabaloos", LocationGroups.McNary, "Calabaloo's serves up fresh-cooked burgers made with local ingredients. Add hot, tasty sides like french fries and onion rings, or a sweet, creamy milkshake. Any burger can be customized, with vegan burgers, grilled chicken, and gluten-free buns all available by request.", 11, 20, 44.56405274971014, -123.27210990844247, listOf("/api/images/calabaloos.png")),
    Location("The Main Squeeze", "the-main-squeeze", LocationGroups.McNary, "Get a freshly blended smoothie made with Oregon-grown berries, or treat yourself to frozen yogurt with your choice of toppings. MainSqueeze also carries grab-and-go snacks, frozen food and cold beverages.", 9, 23, 44.56419163104882, -123.2723429315108, listOf("/api/images/themainsqueeze.png")),
    Location("RainTree Coffee","raintree-coffee", LocationGroups.McNary, "Get hand-crafted espresso drinks made with Starbucks coffee and Teavana tea. Pair your beverage with a savory or sweet pastry, made on campus by University Housing & Dining Services bakers.", 7, 14, 44.56403026739063, -123.27219564748334, listOf("/api/images/raintreecoffeeco.jpeg")),
    Location("Five Four One", "five-four-one", LocationGroups.McNary, "Five Four One serves the best of Pacific Northwest cuisine, with ingredients from local farms and campus gardens. Try daily chef-created entrees, house-made pasta and pizza, soups, sandwiches and desserts. All produce at Five Four One is organic and all ingredients are free of pesticides, high fructose corn syrup, and artficial dyes, flavorings and sweeteners.", 11, 19, 44.56415218040551, -123.27199017018366, listOf("/api/images/fivefourone.png")),
    Location("La Calle", "la-calle", LocationGroups.McNary, "Satisfy your late-night food cravings with tacos, burritos, chips and queso, and other Latin-inspired favorites.", 19, 23, 44.56405925227253, -123.2724240401381, listOf("/api/images/lacalle.png")),

    Location("Bites", "bites", LocationGroups.MU, "Bites convenience store has everything you need! Located on the Mezzanine level of the Memorial Union across from the Trysting Tree Lounge, it is the perfect stop for anything from assorted beverages, to snacks, to various drug store products.  Bites features Corvallis’s own  Pastega Coffee Roasters and the Popcakes robot, making mini pancakes to order.", 7, 19, 44.56494644817311, -123.27846244894907, listOf("/api/images/bites.jpeg")),
    Location("JavaStop", "javastop", LocationGroups.MU, "Located near the main lounge, at the top of the main entry stairs from the quad, JavaStop brews Portland Roasting Coffee and Smith teas, and offers a variety of tasty pastry treats, and irresistible bagel sandwiches.", 7, 19, 44.56495739528206, -123.27876209552703, listOf("/api/images/javastop.jpeg")),
    Location("Off the Quad", "off-the-quad", LocationGroups.MU, "A quiet, sit-down meal location in the Memorial Union along the hallway between the main stairs and the MU Commons. Serving curated menu items that are adjusted seasonally, along with weekly specials. Currently featuring fresh-pressed juice, savory bowls, wraps, burritos and tortas. Breakfast menu includes fresh biscuits with house-made jams, avocado toast and acai. Kombucha, root beer, and Pastega nitro coffee on tap.", 9, 15, 44.56498484299334, -123.27900444465249, listOf("/api/images/offthequad.png")),
    Location("Rocket Burger", "rocket-burger", LocationGroups.MU, "Rocket Burger, conveniently located in the Memorial Union, serves American-style fast food favorites like burgers, fries and crispy chicken sandwiches along with delicious salads and veggie burgers. Rocket Burger’s simple menu and self-order kiosk allow for quick service even around peak meal times!", 10, 20, 44.56500580148071, -123.27958250752957, listOf("/api/images/rocketburger.webp")),
    Location("Panda Express", "panda-express", LocationGroups.MU, "Panda Express is an American fast food restaurant chain that specializes in American Chinese cuisine. ", 10, 20, 44.56484145993573, -123.27941419816489, listOf("/api/images/panda.png")),
    Location("North Porch Cafe", "north-porch-cafe", LocationGroups.MU, "Located in the Memorial Union near SEC Plaza, North Porch serves an Asian fusion theme menu, with made-to-order options for lunch, including rice bowls, Bánh Mì sandwiches, Thai iced tea, and grab-and-go items.", 10, 15, 44.56512854182737, -123.27817586645494, listOf("/api/images/norchporchcafe.jpeg")),

    Location("Global Fare", "global-fare", LocationGroups.Arnold, "A rotating menu of globally inspired daily specials.", 7, 20, 44.5607682191321, -123.27757265633848, listOf("/api/images/globalfare.png")),
    Location("Nori", "nori", LocationGroups.Arnold, "Ramen and teriyaki bowls and Asian-inspired cuisine.", 7, 20, 44.56044300825791, -123.27757323586768, listOf("/api/images/nori.jpeg")),
    Location("The Grill", "grill", LocationGroups.Arnold, "Tasty favorites like fries, chicken tenders, and burgers made with 100% grass-fed beef.", 11, 19, 44.56077926029649, -123.2774755766198, listOf("/api/images/grill.png")),
    Location("Arnold Deli", "arnold-deli", LocationGroups.Arnold, "Custom sandwiches, salad bar, and tossed-to-order salads.", 10, 17, 44.56044178256524, -123.27751955755423, listOf("/api/images/deli.png")),
    Location("Arnold Pizza", "arnold-pizza", LocationGroups.Arnold, "Order fresh-made personal pizza with toppings of your choosing, or order a slice of our chef-created pizza of the day.", 11, 9, 44.560502046094804, -123.27769886651917, listOf("/api/images/pizzeria.png")),
)
