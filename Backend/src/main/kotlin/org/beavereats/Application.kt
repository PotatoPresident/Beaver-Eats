package org.beavereats

import io.github.cdimascio.dotenv.dotenv
import io.ktor.server.application.*
import org.beavereats.plugins.*

val env = dotenv()

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSecurity()
    configureMonitoring()
    configureSerialization()
    configureRouting()
}
