package ru.csit.battleship.gameserver.service.auth

import io.netty.channel.Channel
import ru.csit.battleship.gameserver.networking.session.PlayerSession

interface AuthService {

    fun authenticate(channel: Channel, token: String): PlayerSession
}