package ru.csit.battleship.gameserver.event.player

import com.fasterxml.jackson.annotation.JsonIgnore
import ru.csit.battleship.gameserver.networking.session.PlayerSession

class PlayerShotEvent(

    session: PlayerSession,
    val x: Int,
    val y: Int,
) : AbstractPlayerEvent(session)