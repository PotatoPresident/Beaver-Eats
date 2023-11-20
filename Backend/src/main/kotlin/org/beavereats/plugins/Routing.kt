package org.beavereats.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.beavereats.routes.*
import java.io.File

fun Application.configureRouting() {
    install(StatusPages) {
        exception<AuthenticationException> { call, cause ->
            call.respond(HttpStatusCode.Unauthorized)
        }
        exception<AuthorizationException> { call, cause ->
            call.respond(HttpStatusCode.Forbidden)
        }
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }
    routing {
        route("/api") {
            testRouting()
            locationRouting()
            reviewRouting()
            userRouting()
            authRouting()
            staticResources("/images", "restaurantlLogos")
        }
        testRouting()
        locationRouting()
        reviewRouting()
        userRouting()
        authRouting()
        staticResources("/images", "restaurantlLogos")
    }
}

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()
