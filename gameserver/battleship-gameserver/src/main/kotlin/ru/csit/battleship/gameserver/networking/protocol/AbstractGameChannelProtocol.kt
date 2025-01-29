package ru.csit.battleship.gameserver.networking.protocol

import io.netty.channel.ChannelPipeline
import ru.csit.battleship.gameserver.networking.extension.clearPipeline
import ru.csit.battleship.gameserver.networking.session.Session


abstract class AbstractGameChannelProtocol(val state: ProtocolState) : GameProtocolApplyable {

    override fun <T : Session> apply(playerSession: T, clearExistingProtocolHandlers: Boolean) {
        if (clearExistingProtocolHandlers) {
            val pipeline: ChannelPipeline = playerSession.channelPipeline()
            pipeline.clearPipeline()
        }
        apply(playerSession)
    }

    override fun <T : Session> apply(playerSessions: Collection<T>) {
        playerSessions.forEach(this::apply)
    }
}
