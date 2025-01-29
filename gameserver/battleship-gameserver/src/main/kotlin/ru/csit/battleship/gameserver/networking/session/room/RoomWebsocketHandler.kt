package ru.csit.battleship.gameserver.networking.session.room

import ru.csit.battleship.gameserver.networking.handler.WebsocketHandler
import ru.csit.battleship.gameserver.networking.message.AbstractMessage
import ru.csit.battleship.gameserver.networking.session.PlayerSession
import java.util.*

interface RoomWebsocketHandler : WebsocketHandler {

    val sessions: Map<UUID, PlayerSession>

    fun sendBroadcast(message: AbstractMessage) {
        sessions.values.forEach { s -> send(s.channel, message) }
    }
}