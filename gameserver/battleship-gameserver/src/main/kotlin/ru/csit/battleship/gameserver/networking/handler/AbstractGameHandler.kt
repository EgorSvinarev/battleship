package ru.csit.battleship.gameserver.networking.handler

import io.netty.channel.Channel
import io.netty.channel.ChannelFutureListener
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import mu.KotlinLogging.logger
import ru.csit.battleship.gameserver.dto.error.Error
import ru.csit.battleship.gameserver.event.AbstractEvent
import ru.csit.battleship.gameserver.event.dispatcher.EventDispatcher
import ru.csit.battleship.gameserver.event.listener.EventListener
import ru.csit.battleship.gameserver.networking.message.AbstractMessage
import ru.csit.battleship.gameserver.networking.message.MessageType
import ru.csit.battleship.gameserver.networking.message.MessageType.FAILURE
import ru.csit.battleship.gameserver.networking.message.outgoing.OutgoingMessage

abstract class AbstractGameHandler<T: AbstractMessage>(
    private val eventDispatcher: EventDispatcher<T>
) : SimpleChannelInboundHandler<T>(), WebsocketHandler{

    fun <A : AbstractEvent> addEventListener(
        type: MessageType,
        eventType: Class<A>,
        eventListener: EventListener<A>
    ) {
        eventDispatcher.addEventListener(type, eventType, eventListener)
    }

    public override fun channelRead0(ctx: ChannelHandlerContext, message: T) {
        eventDispatcher.fireEvent(ctx, message)
    }

    protected fun closeChannelWithFailure(ctx: ChannelHandlerContext, message: String) {
        closeChannelWithFailure(ctx.channel(), message)
    }

    private fun closeChannelWithFailure(channel: Channel, message: String) {
        send(channel, OutgoingMessage(FAILURE, Error(message)))
            .addListener(ChannelFutureListener.CLOSE)
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
        log.error(cause.message, cause)
        sendFailure(ctx, cause.message!!)
    }

    companion object {
        val log = logger {}
    }
}