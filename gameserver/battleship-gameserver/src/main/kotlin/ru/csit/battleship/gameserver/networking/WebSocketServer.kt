package ru.csit.battleship.gameserver.networking

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import mu.KotlinLogging.logger
import org.springframework.stereotype.Component
import ru.csit.battleship.gameserver.config.ApplicationProperties
import ru.csit.battleship.gameserver.networking.handler.DefaultWebsocketInitializer

@Component
class WebSocketServer(
    private val properties: ApplicationProperties,
    private val customWebSocketServerInitializer: DefaultWebsocketInitializer
) {

    fun start() {
        log.info { "Websocket server is starting..." }
        log.info {
            "Reserved threads number: ${
                properties.server.threads.gameThreads +
                    properties.server.threads.workerThreads +
                    properties.server.threads.eventloopThreads
            }"
        }

        val boss = NioEventLoopGroup(properties.server.threads.eventloopThreads)
        val worker = NioEventLoopGroup(properties.server.threads.workerThreads)
        val boot = ServerBootstrap()

        boot.group(boss, worker)
            .channel(NioServerSocketChannel::class.java)
            .childHandler(customWebSocketServerInitializer)

        val channelFuture = boot.bind(properties.server.port).sync()
        channelFuture.addListener {
            log.info { "Websocket server has been started with [${properties.server.port}] active port" }
        }
        channelFuture.channel().closeFuture().addListener {
            log.info { "Websocket server is closing..." }
            boss.shutdownGracefully()
            worker.shutdownGracefully()
        }.sync()
    }

    companion object {
        val log = logger {}
    }
}