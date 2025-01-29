package ru.csit.battleship.gameserver.networking.handler

import io.netty.channel.Channel
import io.netty.channel.ChannelFuture
import io.netty.channel.ChannelHandlerContext
import ru.csit.battleship.gameserver.dto.error.Error
import ru.csit.battleship.gameserver.event.player.AbstractPlayerEvent
import ru.csit.battleship.gameserver.networking.message.AbstractMessage
import ru.csit.battleship.gameserver.networking.message.MessageType
import ru.csit.battleship.gameserver.networking.message.outgoing.OutgoingMessage

interface WebsocketHandler {

    fun <E : AbstractPlayerEvent> send(event: E, message: AbstractMessage): ChannelFuture {
        return event.session.channel.writeAndFlush(message)
    }

    fun send(channel: Channel, message: AbstractMessage): ChannelFuture {
        val future = channel.writeAndFlush(message)
        future.addListener {
            channel.flush()
        }
        return future
    }

    fun sendFailure(ctx: ChannelHandlerContext, message: String): ChannelFuture {
        return send(ctx.channel(), OutgoingMessage(MessageType.FAILURE, Error(message)))
    }
}