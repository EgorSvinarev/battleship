package ru.csit.battleship.core

data class Player(
    val name: String,
    val board: Board = Board(),
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        if (other == null || this::class != other::class) return false
        return name == (other as Player).name
    }
}