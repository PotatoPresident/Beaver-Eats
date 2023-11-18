package org.beavereats.routes

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.beavereats.httpClient
import org.beavereats.models.UserInfo
import org.beavereats.plugins.UserSession

fun Route.userRouting() {
    authenticate("session") {
        get("/userinfo") {
            val principal: UserSession = call.principal()!!
            val userInfo: UserInfo = httpClient.get("https://www.googleapis.com/oauth2/v2/userinfo") {
                headers {
                    append(HttpHeaders.Authorization, "Bearer ${principal.token}")
                }
            }.body()
            call.respond(userInfo)
        }
    }
}