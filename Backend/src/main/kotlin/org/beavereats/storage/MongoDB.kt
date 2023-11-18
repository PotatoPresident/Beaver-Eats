package org.beavereats.storage

import com.mongodb.kotlin.client.coroutine.MongoClient
import org.beavereats.env

val mongoClient = MongoClient.create(env["ATLAS_CONNECTION_STRING"])
val database = mongoClient.getDatabase("Cluster0")
