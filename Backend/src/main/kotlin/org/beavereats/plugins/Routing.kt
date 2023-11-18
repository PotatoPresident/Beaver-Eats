package org.beavereats.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.beavereats.routes.locationRouting
import org.beavereats.routes.reviewRouting
import org.beavereats.routes.testRouting

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
        testRouting()
        locationRouting()
        reviewRouting()
    }
}

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()
