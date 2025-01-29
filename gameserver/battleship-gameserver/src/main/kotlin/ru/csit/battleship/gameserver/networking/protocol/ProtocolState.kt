package ru.csit.battleship.gameserver.networking.protocol

enum class ProtocolState(val value: String) {

    GAME_STATE("GAME_STATE"),
    OUT_OF_ROOM_STATE("OUT_OF_ROOM_STATE")
}