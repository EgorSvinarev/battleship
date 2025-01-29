package ru.csit.battleship.gameserver.event.player

import ru.csit.battleship.gameserver.networking.session.PlayerSession

class GameRoomJoinEvent(session: PlayerSession) : AbstractPlayerEvent(session)