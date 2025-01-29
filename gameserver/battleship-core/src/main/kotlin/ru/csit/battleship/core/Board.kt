package ru.csit.battleship.core

import java.util.Random


class Board() {
    private val grid: Array<CharArray> = Array(SIZE) { CharArray(SIZE) }
    private val ships: MutableList<Ship> = ArrayList()

    init {
        for (i in 0 until SIZE) {
            for (j in 0 until SIZE) {
                grid[i][j] = '.'
            }
        }
        placeShips()
    }

    fun getGrid(revealShips: Boolean): Array<Array<String>> {
        val result = Array(SIZE) { Array(SIZE) {"."} }
        for (i in 0 until SIZE) {
            for (j in 0 until SIZE) {
                val cell = grid[i][j]
                if (cell == 'S' && !revealShips) {
                    result[i][j] = "."
                } else {
                    result[i][j] = "$cell"
                }
            }
        }
        return result
    }

    private fun placeShips() {
        val random = Random()

        for (ship in SHIPS) {
            var placed = false
            while (!placed) {
                val horizontal: Boolean = random.nextBoolean()
                var x: Int = random.nextInt(SIZE)
                var y: Int = random.nextInt(SIZE)
                if (canPlaceShip(x, y, ship, horizontal)) {
                    val coordinates: MutableList<Coordinate> = ArrayList()
                    for (i in 0 until ship) {
                        coordinates.add(Coordinate(x, y))
                        if (!horizontal) y++ else x++
                    }
                    ships.add(Ship(coordinates))
                    markShipOnGrid(coordinates)
                    placed = true
                }
            }
        }
        printBoard(true)
    }

    private fun canPlaceShip(x: Int, y: Int, size: Int, isHorizontal: Boolean): Boolean {
        for (k in 0 until size) {
            val (i, j) = if (isHorizontal) {
                x + k to y
            } else {
                x to y + k
            }
            // Проверяем, что клетки в пределах поля и не заняты другим кораблем
            if (i >= SIZE || j >= SIZE || i < 0 || j < 0 || grid[i][j] != '.') {
                return false
            }
        }

        // Вычисление границ области вокруг корабля
        val buffer = if (isHorizontal) {
            arrayOf(
                intArrayOf(if (x == 0) x else x - 1, if (y == 0) y else y - 1),
                intArrayOf(if (x + size - 1 == SIZE - 1) x + size - 1 else x + size, if (y == SIZE - 1) y else y + 1)
            )
        } else {
            arrayOf(
                intArrayOf(if (x == 0) x else x - 1, if (y == 0) y else y - 1),
                intArrayOf(if (x == SIZE - 1) x else x + 1, if (y + size - 1 == SIZE - 1) y + size - 1 else y + size)
            )
        }

        for (i in buffer[0][0]..buffer[1][0]) {
            for (j in buffer[0][1]..buffer[1][1]) {
                if (grid[i][j] != '.') {
                    return false
                }
            }
        }

        return true
    }

    private fun markShipOnGrid(coordinates: List<Coordinate>) {
        for (coordinate in coordinates) {
            grid[coordinate.x][coordinate.y] = 'S'
        }
    }

    private fun markSunkShip(ship: Ship) {

        val minRow = ship.coordinates.minOf { it.x }
        val maxRow = ship.coordinates.maxOf { it.x }
        val minCol = ship.coordinates.minOf { it.y }
        val maxCol = ship.coordinates.maxOf { it.y }

        for (row in (minRow - 1)..(maxRow + 1)) {
            for (col in (minCol - 1)..(maxCol + 1)) {
                // Пропускаем клетки внутри самого ряда
                if (!ship.coordinates.contains(Coordinate(row, col))  &&
                    row in 0 until SIZE &&
                    col in 0 until SIZE
                ) {
                    grid[row][col] = 'O'
                }
            }
        }
    }

    fun takeShot(x: Int, y: Int): Boolean {
        if (grid[x][y] == 'S') {
            grid[x][y] = 'X'
            for (ship in ships) {
                if (ship.contains(x, y)) {
                    ship.hit()
                    if (ship.isSunk) {
                        markSunkShip(ship)
                    }

                    return true
                }
            }
        } else if (grid[x][y] == '.') {
            grid[x][y] = 'O'
        }
        return false
    }

    fun allShipsSunk(): Boolean {
        for (ship in ships) {
            if (!ship.isSunk) return false
        }
        return true
    }

    fun printBoard(revealShips: Boolean) {
        println("  0 1 2 3 4 5 6 7 8 9")
        for (i in 0 until SIZE) {
            print("$i ")
            for (j in 0 until SIZE) {
                val cell = grid[i][j]
                if (cell == 'S' && !revealShips) {
                    print(". ")
                } else {
                    print("$cell ")
                }
            }
            println()
        }
    }

    companion object {
        private const val SIZE = 10
        private val SHIPS = arrayOf(4, 3, 3, 2, 2, 2, 1, 1, 1, 1)
    }
}

