package ru.csit.battleship.gameserver.event.player

import ru.csit.battleship.gameserver.networking.session.PlayerSession

class PlayerReadyEvent(session: PlayerSession) : AbstractPlayerEvent(session)