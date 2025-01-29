package ru.csit.battleship.gameserver.service.auth

import io.netty.channel.Channel
import org.springframework.stereotype.Service
import ru.csit.battleship.gameserver.networking.session.PlayerSession
import java.util.*

@Service
class AuthServiceImpl : AuthService {

    override fun authenticate(channel: Channel, token: String) =
        PlayerSession.createPlayerSession(channel, UUID.randomUUID())
}