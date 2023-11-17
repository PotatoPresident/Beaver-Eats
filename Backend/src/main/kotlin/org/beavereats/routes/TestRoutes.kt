package org.beavereats.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import org.beavereats.plugins.UserSession

fun Route.testRouting() {
    get("/home") {
        val userSession: UserSession? = call.sessions.get()
        if (userSession != null) {
            call.respondText("Welcome $userSession")
        } else {
            call.respondText("Please login")
        }
    }
}