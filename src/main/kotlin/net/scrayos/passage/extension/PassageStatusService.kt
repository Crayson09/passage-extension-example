package net.scrayos.passage.extension

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import scrayosnet.passage.adapter.StatusGrpcKt
import scrayosnet.passage.adapter.StatusOuterClass
import scrayosnet.passage.adapter.playerEntry
import scrayosnet.passage.adapter.players
import scrayosnet.passage.adapter.protocolVersion
import scrayosnet.passage.adapter.statusData
import scrayosnet.passage.adapter.statusResponse
import java.util.UUID

class PassageStatusService : StatusGrpcKt.StatusCoroutineImplBase() {

    override suspend fun getStatus(request: StatusOuterClass.StatusRequest): StatusOuterClass.StatusResponse =
        statusResponse {
            status = statusData {
                version = protocolVersion {
                    name = "1.21.6"
                    protocol = request.protocol.toInt()
                }
                players = players {
                    online = 524
                    max = 1000
                    samples.addAll(
                        listOf(
                            playerEntry {
                                name = "Entry 1"
                                id = UUID.randomUUID().toString()
                            },
                            playerEntry {
                                name = "Entry 2"
                                id = UUID.randomUUID().toString()
                            },
                        ),
                    )
                }

                description = GsonComponentSerializer.gson().serialize(
                    Component.text("Hello World!"),
                )

                enforcesSecureChat = false
            }
        }
}
