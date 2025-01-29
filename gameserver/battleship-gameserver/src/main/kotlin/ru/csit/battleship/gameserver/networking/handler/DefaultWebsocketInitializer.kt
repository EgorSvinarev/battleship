package ru.csit.battleship.gameserver.networking.handler

import io.netty.channel.Channel
import io.netty.channel.ChannelInitializer
import io.netty.handler.codec.http.HttpObjectAggregator
import io.netty.handler.codec.http.HttpRequestDecoder
import io.netty.handler.codec.http.HttpResponseEncoder
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler
import org.springframework.stereotype.Component
import ru.csit.battleship.gameserver.networking.ServerConstants.DEFAULT_OBJECT_AGGREGATOR_CONTENT_LENGTH
import ru.csit.battleship.gameserver.networking.ServerConstants.HandlerNames.AGGREGATOR
import ru.csit.battleship.gameserver.networking.ServerConstants.HandlerNames.DECODER
import ru.csit.battleship.gameserver.networking.ServerConstants.HandlerNames.ENCODER
import ru.csit.battleship.gameserver.networking.ServerConstants.HandlerNames.HANDLER
import ru.csit.battleship.gameserver.networking.ServerConstants.HandlerNames.INIT_HANDLER
import ru.csit.battleship.gameserver.networking.ServerConstants.HandlerNames.PING_PONG_HANDLER
import ru.csit.battleship.gameserver.networking.ServerConstants.HandlerNames.TXT_WS_DECODER
import ru.csit.battleship.gameserver.networking.ServerConstants.WEBSOCKET_PATH
import ru.csit.battleship.gameserver.networking.adapter.TextWebsocketDecoder

@Component
class DefaultWebsocketInitializer(
    private val websocketHandlerMain: InitialGameHandler,
    private val websocketDecoder: TextWebsocketDecoder,
    private val pingWebsocketHandler: PingWebsocketHandler
) : ChannelInitializer<Channel>() {


    override fun initChannel(channel: Channel) {
        val channelPipeline = channel.pipeline()
        channelPipeline.addLast(DECODER, HttpRequestDecoder())
        channelPipeline.addLast(AGGREGATOR, HttpObjectAggregator(DEFAULT_OBJECT_AGGREGATOR_CONTENT_LENGTH))
        channelPipeline.addLast(HANDLER, WebSocketServerProtocolHandler(WEBSOCKET_PATH))
        channelPipeline.addLast(PING_PONG_HANDLER, pingWebsocketHandler)
        channelPipeline.addLast(TXT_WS_DECODER, websocketDecoder)
        channelPipeline.addLast(INIT_HANDLER, websocketHandlerMain)
        channelPipeline.addLast(ENCODER, HttpResponseEncoder())
    }
}