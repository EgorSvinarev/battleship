package ru.csit.battleship

import org.springframework.boot.runApplication

object LocalRunner {

    @JvmStatic
    fun main(vararg args: String) {
        System.setProperty("spring.profiles.active", "local")
        BattleshipApplication.main(*args)
    }
}