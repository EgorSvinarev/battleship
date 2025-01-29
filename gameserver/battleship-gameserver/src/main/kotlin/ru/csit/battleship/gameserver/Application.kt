package ru.csit.battleship.gameserver

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import ru.csit.battleship.gameserver.config.ApplicationProperties
import ru.csit.battleship.gameserver.networking.WebSocketServer

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties::class)
class Application(
    private val server: WebSocketServer
) : CommandLineRunner {

    companion object {
        @JvmStatic
        fun main(vararg args: String) {
            runApplication<Application>(*args)
        }
    }

    override fun run(vararg args: String?) {
        server.start()
    }
}