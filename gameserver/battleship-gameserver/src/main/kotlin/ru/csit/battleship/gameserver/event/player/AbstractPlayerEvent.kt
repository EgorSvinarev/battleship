package ru.csit.battleship.gameserver.event.player

import ru.csit.battleship.gameserver.event.AbstractEvent
import ru.csit.battleship.gameserver.networking.session.PlayerSession

abstract class AbstractPlayerEvent(open var session: PlayerSession) : AbstractEvent()