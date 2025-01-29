package ru.csit.battleship.gameserver.networking.message.incoming

import ru.csit.battleship.gameserver.networking.message.AbstractMessage
import ru.csit.battleship.gameserver.networking.message.MessageType

open class IncomingMessage(type: MessageType, data: String) : AbstractMessage(type, data)