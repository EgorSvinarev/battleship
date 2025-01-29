package ru.csit.battleship.gameserver.networking.message.outgoing

import ru.csit.battleship.gameserver.networking.message.AbstractMessage
import ru.csit.battleship.gameserver.networking.message.MessageType

open class OutgoingMessage : AbstractMessage {
    constructor(type: MessageType) : super(type, null)
    constructor(type: MessageType, data: Any?) : super(type, data)
}

