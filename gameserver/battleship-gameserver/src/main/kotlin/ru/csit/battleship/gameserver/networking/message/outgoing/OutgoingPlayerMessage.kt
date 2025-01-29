package ru.csit.battleship.gameserver.networking.message.outgoing

import ru.csit.battleship.gameserver.networking.message.MessageType
import ru.csit.battleship.gameserver.networking.session.PlayerSession


class OutgoingPlayerMessage : OutgoingMessage {

    lateinit var playerSession: PlayerSession

    constructor(type: MessageType) : super(type, null)
    constructor(playerSession: PlayerSession, type: MessageType, source: Any?) : super(type, source) {
        this.playerSession = playerSession
    }
}
