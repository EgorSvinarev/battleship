package ru.csit.battleship.gameserver.networking.extension

import io.netty.channel.Channel
import io.netty.channel.ChannelPipeline
import java.net.InetSocketAddress

fun ChannelPipeline.clearPipeline() {
    var counter = 0
    while (this.first() != null) {
        this.removeFirst()
        counter++
    }
}

fun Channel.channelIp() = (this as InetSocketAddress).address.toString().substring(1)