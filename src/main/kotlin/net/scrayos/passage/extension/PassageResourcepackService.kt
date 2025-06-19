package net.scrayos.passage.extension

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import scrayosnet.passage.adapter.ResourcepackGrpcKt
import scrayosnet.passage.adapter.ResourcepackOuterClass
import scrayosnet.passage.adapter.pack
import scrayosnet.passage.adapter.packsResponse
import java.util.UUID

class PassageResourcepackService : ResourcepackGrpcKt.ResourcepackCoroutineImplBase() {

    override suspend fun getPacks(request: ResourcepackOuterClass.PacksRequest): ResourcepackOuterClass.PacksResponse =
        packsResponse {
            packs.add(
                pack {
                    uuid = UUID.randomUUID().toString()
                    url = "https://example.com/resourcepack.zip"
                    hash = "b89eaac7e61417341b710b727768294d0e6a277b"
                    forced = true
                    promptMessage = GsonComponentSerializer.gson().serialize(
                        Component.text("This is important to download!"),
                    )
                },
            )
        }
}
