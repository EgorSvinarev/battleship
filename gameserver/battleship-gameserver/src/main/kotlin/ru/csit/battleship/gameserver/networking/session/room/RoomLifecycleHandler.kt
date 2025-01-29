package ru.csit.battleship.gameserver.networking.session.room

import ru.csit.battleship.gameserver.event.player.PlayerShotEvent
import ru.csit.battleship.gameserver.networking.session.PlayerSession

interface RoomLifecycleHandler {

    fun onRoomCreated()

    fun start()

    fun onRoomStarted()

    fun onDisconnect(session: PlayerSession)

    fun onPlayerReady(session: PlayerSession)

    fun onPlayersNotReady()

    fun onPlayerTimeout()

    fun onPlayerShot(session: PlayerSession, event: PlayerShotEvent)

    fun destroy()

    fun onDestroy()

    fun update()
}