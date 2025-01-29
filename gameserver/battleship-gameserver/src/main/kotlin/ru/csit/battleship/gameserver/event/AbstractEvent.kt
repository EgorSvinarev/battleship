package ru.csit.battleship.gameserver.event

import io.netty.channel.Channel

abstract class AbstractEvent(var channel: Channel? = null) {
}