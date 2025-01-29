package ru.csit.battleship.core


class Game(firstPlayer: Player, secondPlayer: Player) {
    var currentPlayer: Player
        private set
    var opponent: Player
        private set

    init {
        currentPlayer = firstPlayer
        opponent = secondPlayer
    }

    fun switchTurn() {
        val temp = currentPlayer
        currentPlayer = opponent
        opponent = temp
    }

    fun takeShot(x: Int, y: Int) =
        opponent.board.takeShot(x, y)


    fun isCurrentPlayerWin() =
        opponent.board.allShipsSunk()

    fun getOpponent(player: Player): Player {
        if (player == currentPlayer) return opponent
        return currentPlayer
    }
}