package ru.csit.battleship.gameserver.networking.session

import io.netty.channel.Channel
import io.netty.channel.ChannelHandlerContext
import io.netty.util.Attribute
import io.netty.util.AttributeKey
import ru.csit.battleship.core.Player
import java.util.*

class PlayerSession private constructor(
    channel: Channel,
    uuid: UUID
) : Session(channel, uuid) {

    var player: Player? = null
    var roomId: UUID? = null

    override fun toString(): String {
        return "PlayerSession [id = $uuid ${if (player == null) "" else "| player =  $player"}| roomId = $roomId]"
    }

    companion object {

        fun createPlayerSession(channel: Channel, uuid: UUID): PlayerSession {
            return addPlayerSessionToChannel(channel, PlayerSession(channel, uuid))
        }

        private fun addPlayerSessionToChannel(channel: Channel, session: PlayerSession): PlayerSession {
            val storedSession: Attribute<PlayerSession> = channel.attr(PLAYER_SESSION_ATTRIBUTE_KEY)
            storedSession.compareAndSet(null, session)

            return session
        }

        fun getPlayerFromChannelHandlerContext(ctx: ChannelHandlerContext): PlayerSession? =
            getPlayerFromChannel(ctx.channel())

        fun getPlayerFromChannel(channel: Channel): PlayerSession? =
            channel.attr(PLAYER_SESSION_ATTRIBUTE_KEY).get()

        fun removePlayerSessionFromChannel(channel: Channel) {
            val sessionAttr: Attribute<PlayerSession?> = channel.attr(PLAYER_SESSION_ATTRIBUTE_KEY)
            sessionAttr.set(null)
        }

        private final val PLAYER_SESSION_ATTRIBUTE_KEY = AttributeKey.valueOf<PlayerSession>("PLX_SESSION")
    }
}