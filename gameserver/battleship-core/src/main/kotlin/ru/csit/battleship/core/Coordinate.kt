package ru.csit.battleship.core

import java.util.*


class Coordinate(var x: Int, var y: Int) {

    override fun equals(`object`: Any?): Boolean {
        if (this === `object`) return true
        return if (`object` !is Coordinate) false else x == `object`.x && y == `object`.y
    }

    override fun hashCode(): Int {
        return Objects.hash(x, y)
    }
}

