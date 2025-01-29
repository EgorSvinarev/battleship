package ru.csit.battleship.gameserver.networking.message.outgoing

data class BoardConfigMessage (
    val playerBoard: Array<Array<String>>,
    val opponentBoard: Array<Array<String>>
)