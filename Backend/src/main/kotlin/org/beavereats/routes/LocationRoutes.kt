package org.beavereats.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import org.beavereats.models.Location
import org.beavereats.models.LocationGroups
import org.beavereats.models.locations

@Serializable
private data class LocationParent(val name: String, val options: List<Location>)

fun Route.locationRouting() {
    route("/locations") {
        get {
            call.respond(locations.groupBy { it.group.displayName }.mapValues { LocationParent(it.key, it.value) }.values)
        }

        get("{group}") {
            val parent = try {
                LocationGroups.valueOf(call.parameters["group"]!!)
            } catch (e: Exception) {
                return@get call.respondText(
                    "Invalid group",
                    status = HttpStatusCode.BadRequest
                )
            }

            val location = locations.filter { it.group == parent }
            if (location.isEmpty())
                return@get call.respondText("No location with parent $parent", status = HttpStatusCode.NotFound)

            call.respond(location)
        }
    }
}