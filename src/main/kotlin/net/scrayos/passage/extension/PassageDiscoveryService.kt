package net.scrayos.passage.extension

import scrayosnet.passage.adapter.DiscoveryGrpcKt
import scrayosnet.passage.adapter.DiscoveryOuterClass
import scrayosnet.passage.adapter.address
import scrayosnet.passage.adapter.metaEntry
import scrayosnet.passage.adapter.target
import scrayosnet.passage.adapter.targetsResponse

class PassageDiscoveryService : DiscoveryGrpcKt.DiscoveryCoroutineImplBase() {

    override suspend fun getTargets(request: DiscoveryOuterClass.TargetRequest): DiscoveryOuterClass.TargetsResponse =
        targetsResponse {
            targets.add(
                target {
                    identifier = "hub-1"
                    address = address {
                        hostname = "127.0.0.1"
                        port = 25565
                    }
                    meta.add(
                        metaEntry {
                            key = "name"
                            value = "Hub 1"
                        },
                    )
                },
            )
        }
}
