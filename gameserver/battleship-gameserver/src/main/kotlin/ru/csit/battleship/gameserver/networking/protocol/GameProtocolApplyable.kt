package ru.csit.battleship.gameserver.networking.protocol

import ru.csit.battleship.gameserver.networking.session.Session

interface GameProtocolApplyable {

    fun <T : Session> apply(playerSession: T)

    fun <T : Session> apply(playerSession: T, clearExistingProtocolHandlers: Boolean)

    fun <T : Session> apply(playerSessions: Collection<T>)
}