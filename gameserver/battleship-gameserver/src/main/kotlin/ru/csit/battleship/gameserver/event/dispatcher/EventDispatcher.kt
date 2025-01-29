package ru.csit.battleship.gameserver.event.dispatcher

import com.google.gson.Gson
import io.netty.channel.ChannelHandlerContext
import ru.csit.battleship.gameserver.config.Constants.EMPTY_JSON
import ru.csit.battleship.gameserver.event.AbstractEvent
import ru.csit.battleship.gameserver.event.player.AbstractPlayerEvent
import ru.csit.battleship.gameserver.event.listener.EventListener
import ru.csit.battleship.gameserver.networking.message.AbstractMessage
import ru.csit.battleship.gameserver.networking.message.MessageType
import ru.csit.battleship.gameserver.networking.message.incoming.IncomingPlayerMessage
import java.util.*


class EventDispatcher<T : AbstractMessage> {

    private val objectMapper = Gson()
    private val typeToEventType: MutableMap<MessageType, Class<out AbstractEvent>> = EnumMap(MessageType::class.java)
    private val eventListenerMap: MutableMap<MessageType, EventListener<out AbstractEvent>> = EnumMap(MessageType::class.java)

    fun <A : AbstractEvent> addEventListener(
        type: MessageType,
        eventType: Class<A>,
        eventListener: EventListener<out AbstractEvent>
    ) {
        typeToEventType[type] = eventType
        eventListenerMap[type] = eventListener
    }

    fun fireEvent(ctx: ChannelHandlerContext, message: T) {
        if (!typeToEventType.containsKey(message.type)) return

        val eventType = typeToEventType[message.type]
        val event = objectMapper.fromJson(
            if (message.data != null) message.data.toString() else EMPTY_JSON,
            eventType
        )

        if (event is AbstractPlayerEvent && message is IncomingPlayerMessage)
            event.session = message.playerSession
        if (event is AbstractEvent)
            event.channel = ctx.channel()
        if (!eventListenerMap.containsKey(message.type))
            throw RuntimeException("Incorrect message type")

        fireEvent(event, eventListenerMap[message.type]!!)
    }

    private fun <E : AbstractEvent> fireEvent(event: E, eventListener: EventListener<out AbstractEvent>) {
        (eventListener as EventListener<E>).onEvent(event)
    }
}

