package ru.csit.battleship.gameserver.networking.handler

import io.netty.channel.ChannelHandler.Sharable
import io.netty.channel.ChannelHandlerContext
import org.springframework.stereotype.Component
import ru.csit.battleship.gameserver.event.dispatcher.EventDispatcher
import ru.csit.battleship.gameserver.event.player.GameRoomJoinEvent
import ru.csit.battleship.gameserver.networking.message.MessageType.JOIN
import ru.csit.battleship.gameserver.networking.message.incoming.IncomingPlayerMessage
import ru.csit.battleship.gameserver.networking.session.PlayerSession
import ru.csit.battleship.gameserver.service.gameroom.GameRoomService

@Component
@Sharable
class OutOfRoomGameHandler(
    private val gameRoomService: GameRoomService
) : AbstractGameHandler<IncomingPlayerMessage>(EventDispatcher()) {

    init {
        addEventListener(JOIN, GameRoomJoinEvent::class.java) { event ->
            gameRoomService.addPlayerToQueue(event)
        }
    }

    override fun channelInactive(ctx: ChannelHandlerContext) {
        gameRoomService.removePlayerFromQueue(PlayerSession.getPlayerFromChannelHandlerContext(ctx))
    }
}