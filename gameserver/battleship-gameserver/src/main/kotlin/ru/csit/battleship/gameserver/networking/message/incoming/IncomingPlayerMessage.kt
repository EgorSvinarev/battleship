package ru.csit.battleship.gameserver.networking.message.incoming

import ru.csit.battleship.gameserver.networking.message.MessageType
import ru.csit.battleship.gameserver.networking.session.PlayerSession

class IncomingPlayerMessage : IncomingMessage {

    lateinit var playerSession: PlayerSession

    constructor(playerSession: PlayerSession, type: MessageType, data: String) : super(type, data) {
        this.playerSession = playerSession
    }
}
