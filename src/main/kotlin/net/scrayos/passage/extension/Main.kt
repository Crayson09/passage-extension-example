package net.scrayos.passage.extension

import io.grpc.ServerBuilder

fun main() {
    val server = ServerBuilder
        .forPort(3030)
        .addService(PassageStatusService())
        .addService(PassageResourcepackService())
        .addService(PassageDiscoveryService())
        .addService(PassageStrategyService())
        .build()

    server.start()
    server.awaitTermination()
}
