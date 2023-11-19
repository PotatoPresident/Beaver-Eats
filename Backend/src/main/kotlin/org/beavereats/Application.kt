package org.beavereats

import io.github.cdimascio.dotenv.dotenv
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.beavereats.models.locations
import org.beavereats.plugins.configureMonitoring
import org.beavereats.plugins.configureRouting
import org.beavereats.plugins.configureSecurity
import org.beavereats.plugins.configureSerialization

val env = dotenv {
    ignoreIfMissing = true
}
val httpClient = HttpClient(Apache) {
    install (ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
        })
    }
}

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSecurity()
    configureMonitoring()
    configureSerialization()
    configureRouting()

    runBlocking {
        locations.forEach {
            it.updateRating()
        }
    }
}
