package ru.csit.battleship.gameserver.networking.protocol

import mu.KotlinLogging.logger
import org.springframework.stereotype.Component
import ru.csit.battleship.gameserver.networking.ServerConstants.HandlerNames.INIT_HANDLER
import ru.csit.battleship.gameserver.networking.ServerConstants.HandlerNames.EVENT_HANDLER
import ru.csit.battleship.gameserver.networking.ServerConstants.HandlerNames.TXT_WS_DECODER
import ru.csit.battleship.gameserver.networking.ServerConstants.HandlerNames.TXT_WS_ENCODER
import ru.csit.battleship.gameserver.networking.adapter.TextWebsocketDecoder
import ru.csit.battleship.gameserver.networking.adapter.TextWebsocketEncoder
import ru.csit.battleship.gameserver.networking.handler.OutOfRoomGameHandler
import ru.csit.battleship.gameserver.networking.protocol.ProtocolState.GAME_STATE
import ru.csit.battleship.gameserver.networking.session.Session

@Component
class OutOfRoomProtocol(
    private val decoder: TextWebsocketDecoder,
    private val encoder: TextWebsocketEncoder,
    private val handler: OutOfRoomGameHandler,
) : AbstractGameChannelProtocol(GAME_STATE) {

    override fun <T : Session> apply(playerSession: T, clearExistingProtocolHandlers: Boolean) =
        apply(playerSession)

    override fun <T : Session> apply(playerSession: T) {
        log.info { "Going to apply $state on session: $playerSession" }

        val pipeline = playerSession.channelPipeline()

        if (pipeline.names().contains(INIT_HANDLER))
            pipeline.remove(INIT_HANDLER)
        if (pipeline.names().contains(EVENT_HANDLER))
            pipeline.remove(EVENT_HANDLER)
        if (!pipeline.names().contains(TXT_WS_DECODER))
            pipeline.addLast(TXT_WS_DECODER, decoder)
        if (!pipeline.names().contains(TXT_WS_ENCODER))
            pipeline.addLast(TXT_WS_ENCODER, encoder)
        pipeline.addLast(EVENT_HANDLER, handler)
    }

    companion object {
        val log = logger {}
    }
}