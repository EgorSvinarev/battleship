package ru.csit.battleship.gameserver

object LocalRunner {

    @JvmStatic
    fun main(vararg args: String) {
        System.setProperty("spring.profiles.active", "local")
        Application.main(*args)
    }
}