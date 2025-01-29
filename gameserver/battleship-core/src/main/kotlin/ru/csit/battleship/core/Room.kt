package ru.csit.battleship.core

class Room(firstPlayer: Player, secondPlayer: Player) {

    var currentPlayer = firstPlayer
        private set
    private var opponentPlayer = secondPlayer

    private fun switchTurn() {
        val temp = currentPlayer
        currentPlayer = opponentPlayer
        opponentPlayer = temp
    }
}