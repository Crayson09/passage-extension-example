package net.scrayos.passage.extension

import scrayosnet.passage.adapter.StrategyGrpcKt
import scrayosnet.passage.adapter.StrategyOuterClass
import scrayosnet.passage.adapter.selectResponse

class PassageStrategyService : StrategyGrpcKt.StrategyCoroutineImplBase() {

    override suspend fun selectTarget(request: StrategyOuterClass.SelectRequest): StrategyOuterClass.SelectResponse =
        selectResponse {
            val target = request.targetsList.firstOrNull()
            if (target != null) {
                identifier = target.identifier
            }
        }
}
