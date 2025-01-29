package ru.csit.battleship.gameserver.networking.handler

import io.netty.channel.ChannelHandler.Sharable
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame
import org.springframework.stereotype.Component

@Sharable
@Component
class PingWebsocketHandler : SimpleChannelInboundHandler<PingWebSocketFrame>() {

    override fun channelRead0(ctx: ChannelHandlerContext, frame: PingWebSocketFrame) {
        ctx.write(PongWebSocketFrame(frame.content().retain()))
    }
}