package ru.csit.battleship.gameserver.networking.handler

import io.netty.channel.ChannelHandler.Sharable
import io.netty.channel.ChannelHandlerContext
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component
import ru.csit.battleship.gameserver.event.dispatcher.EventDispatcher
import ru.csit.battleship.gameserver.event.player.PlayerReadyEvent
import ru.csit.battleship.gameserver.event.player.PlayerShotEvent
import ru.csit.battleship.gameserver.networking.message.MessageType.PLAYER_READY
import ru.csit.battleship.gameserver.networking.message.MessageType.PLAYER_SHOT
import ru.csit.battleship.gameserver.networking.message.incoming.IncomingPlayerMessage
import ru.csit.battleship.gameserver.networking.session.PlayerSession
import ru.csit.battleship.gameserver.service.gameroom.GameRoomService

@Component
@Sharable
class GameProtocolHandler(
    @Lazy
    private val gameRoomService: GameRoomService
) : AbstractGameHandler<IncomingPlayerMessage>(EventDispatcher()) {

    override fun channelInactive(ctx: ChannelHandlerContext) {
        val session = PlayerSession.getPlayerFromChannelHandlerContext(ctx)!!
        val room = gameRoomService.getRoomById(session.roomId!!)
        room.onDisconnect(session)
    }

    init {
        addEventListener(PLAYER_SHOT, PlayerShotEvent::class.java) { event ->
            val room = gameRoomService.getRoomById(event.session.roomId!!)
            room.onPlayerShot(event.session, event)
        }
    }

    init {
        addEventListener(PLAYER_READY, PlayerReadyEvent::class.java) { event ->
            val room = gameRoomService.getRoomById(event.session.roomId!!)
            room.onPlayerReady(event.session)
        }
    }
}