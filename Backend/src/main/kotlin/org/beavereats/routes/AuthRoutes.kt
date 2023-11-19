package org.beavereats.routes

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import org.beavereats.httpClient
import org.beavereats.models.UserInfo
import org.beavereats.plugins.UserSession
import org.beavereats.plugins.redirects

fun Route.authRouting() {
    authenticate("auth-oauth-google") {
        get("login") {
            // Redirects to 'authorizeUrl`
        }

        get("/callback") {
            val principal: OAuthAccessTokenResponse.OAuth2? = call.principal()
            val userInfo: UserInfo = httpClient.get("https://www.googleapis.com/oauth2/v2/userinfo") {
                headers {
                    append(HttpHeaders.Authorization, "Bearer ${principal!!.accessToken}")
                }
            }.body()
            call.sessions.set(UserSession(principal!!.state!!, principal.accessToken, userInfo.id))
            val redirectUrl = redirects[principal.state]
            call.respondRedirect(redirectUrl!!)
        }
    }

    get("/logout") {
        call.sessions.clear<UserSession>()
        call.respondRedirect("/")
    }
}
