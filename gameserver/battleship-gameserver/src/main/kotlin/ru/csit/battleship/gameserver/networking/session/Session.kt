package ru.csit.battleship.gameserver.networking.session

import io.netty.channel.Channel
import io.netty.channel.ChannelPipeline
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

abstract class Session(
    val channel: Channel,
    val uuid: UUID
) {

    protected val attributes: MutableMap<String, Any> = ConcurrentHashMap()

    fun setAttribute(key: String, value: Any) = attributes.put(key, value)

    fun removeAttribute(key: String) = attributes.remove(key)

    fun channelPipeline(): ChannelPipeline = channel.pipeline()
}