package ru.csit.battleship.core


class Ship(
    val coordinates: List<Coordinate>,
    private var hits: Int = 0
) {

    val isSunk: Boolean
        get() = hits == coordinates.size

    fun contains(x: Int, y: Int) =
        coordinates.any { it.x == x && it.y == y }

    fun hit() {
        hits++
    }
}