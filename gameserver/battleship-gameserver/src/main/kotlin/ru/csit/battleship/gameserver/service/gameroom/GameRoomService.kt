package ru.csit.battleship.gameserver.service.gameroom

import ru.csit.battleship.gameserver.event.player.GameRoomJoinEvent
import ru.csit.battleship.gameserver.networking.session.PlayerSession
import ru.csit.battleship.gameserver.networking.session.room.GameRoom
import java.util.*

interface GameRoomService {

    fun addPlayerToQueue(event: GameRoomJoinEvent)

    fun removePlayerFromQueue(session: PlayerSession?)

    fun getRoomById(id: UUID): GameRoom
}