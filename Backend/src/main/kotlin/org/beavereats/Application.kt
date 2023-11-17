package org.beavereats

import io.ktor.server.application.*
import org.beavereats.plugins.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSecurity()
    configureMonitoring()
    configureSerialization()
    configureRouting()
}
