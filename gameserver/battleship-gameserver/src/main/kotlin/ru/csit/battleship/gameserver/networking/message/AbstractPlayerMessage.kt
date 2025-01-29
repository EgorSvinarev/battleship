package ru.csit.battleship.gameserver.networking.message

import ru.csit.battleship.gameserver.networking.session.PlayerSession

abstract class AbstractPlayerMessage(type: MessageType, data: String) : AbstractMessage(type, data) {

    protected lateinit var playerSession: PlayerSession
}