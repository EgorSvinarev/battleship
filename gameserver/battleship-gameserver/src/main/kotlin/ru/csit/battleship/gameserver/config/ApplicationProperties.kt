package ru.csit.battleship.gameserver.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("application")
data class ApplicationProperties(
    val server: Server,
) {

    data class Server(
        val threads: Threads,
        val port: Int
    ) {
        data class Threads(
            val workerThreads: Int,
            val eventloopThreads: Int,
            val gameThreads: Int,
        )
    }
}