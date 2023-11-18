package org.beavereats.routes

import com.mongodb.client.model.Filters.eq
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.flow.toList
import org.beavereats.models.Review
import org.beavereats.models.reviews
import org.beavereats.plugins.UserSession

fun Route.reviewRouting() {
    route("/review") {
        get("{location}") {
            val location = call.parameters["location"] ?: return@get call.respondText(
                "Missing or malformed location",
                status = HttpStatusCode.BadRequest
            )

            val reviews = reviews.find(eq(Review::locationId.name, location)).toList()
            call.respond(reviews)
        }
        authenticate("session") {
            post {
                val params = call.receiveParameters()
                val location = params["locationId"] ?: return@post call.respondText(
                    "Missing locationId",
                    status = HttpStatusCode.BadRequest
                )
                val rating = params["rating"] ?: return@post call.respondText(
                    "Missing rating",
                    status = HttpStatusCode.BadRequest
                )
                val comment = params["comment"] ?: return@post call.respondText(
                    "Missing comment",
                    status = HttpStatusCode.BadRequest
                )
                val userId = call.principal<UserSession>()!!.id
                val review = Review(userId, location, rating.toInt(), comment)
                if (reviews.insertOne(review).wasAcknowledged()) {
                    call.respondText("Review stored correctly", status = HttpStatusCode.Created)
                } else {
                    call.respondText("Error storing review", status = HttpStatusCode.InternalServerError)
                }
            }
        }
    }
}