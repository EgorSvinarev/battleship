package ru.csit.battleship.gameserver.networking.handler

import io.netty.channel.ChannelHandler.Sharable
import org.springframework.stereotype.Component
import ru.csit.battleship.gameserver.event.AuthEvent
import ru.csit.battleship.gameserver.event.dispatcher.EventDispatcher
import ru.csit.battleship.gameserver.networking.message.MessageType.AUTHENTICATION
import ru.csit.battleship.gameserver.networking.message.incoming.IncomingMessage
import ru.csit.battleship.gameserver.networking.message.outgoing.OutgoingMessage
import ru.csit.battleship.gameserver.networking.protocol.OutOfRoomProtocol
import ru.csit.battleship.gameserver.service.auth.AuthService

@Sharable
@Component
class InitialGameHandler(
    private val outOfRoomProtocol: OutOfRoomProtocol,
    private val authService: AuthService
) : AbstractGameHandler<IncomingMessage>(EventDispatcher()) {

    init {
        addEventListener(AUTHENTICATION, AuthEvent::class.java) { event ->
            outOfRoomProtocol.apply(authService.authenticate(event.channel!!, event.bearerToken))
            send(event.channel!!, OutgoingMessage(AUTHENTICATION))
        }
    }
}