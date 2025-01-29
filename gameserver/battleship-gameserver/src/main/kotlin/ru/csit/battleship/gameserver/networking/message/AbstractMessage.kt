package ru.csit.battleship.gameserver.networking.message

abstract class AbstractMessage(
    var type: MessageType,
    var data: Any?
)