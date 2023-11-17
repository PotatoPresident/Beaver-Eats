package org.beavereats.plugins

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.apache.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.http.headers
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import org.beavereats.env
import org.beavereats.httpClient
import org.beavereats.models.UserInfo

fun Application.configureSecurity() {
    authentication {
        oauth("auth-oauth-google") {
            urlProvider = { "http://localhost:3000/callback" }
            providerLookup = {
                OAuthServerSettings.OAuth2ServerSettings(
                    name = "google",
                    authorizeUrl = "https://accounts.google.com/o/oauth2/auth",
                    accessTokenUrl = "https://accounts.google.com/o/oauth2/token",
                    requestMethod = HttpMethod.Post,
                    clientId = env["GOOGLE_CLIENT_ID"],
                    clientSecret = env["GOOGLE_CLIENT_SECRET"],
                    defaultScopes = listOf("https://www.googleapis.com/auth/userinfo.profile")
                )
            }
            client = httpClient
        }
        session<UserSession> {
            validate { userSession ->
                userSession
            }
            challenge {
                call.respondRedirect("/login")
            }
        }
    }
    install(Sessions) {
        cookie<UserSession>("user_session")
    }
    routing {
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
                call.respondRedirect("/home")
            }
        }
    }
}

data class UserSession(val state: String, val token: String, val id: String): Principal