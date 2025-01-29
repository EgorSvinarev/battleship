package ru.csit.battleship.gameserver.networking.adapter

import com.google.gson.Gson
import io.netty.channel.ChannelHandler.Sharable
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame
import org.springframework.stereotype.Component
import ru.csit.battleship.gameserver.networking.message.incoming.IncomingMessage
import ru.csit.battleship.gameserver.networking.message.incoming.IncomingPlayerMessage
import ru.csit.battleship.gameserver.networking.session.PlayerSession

@Sharable
@Component
class TextWebsocketDecoder(
    private val gson: Gson = Gson()
): MessageToMessageDecoder<TextWebSocketFrame>() {

    override fun decode(ctx: ChannelHandlerContext, frame: TextWebSocketFrame, out: MutableList<Any>) {
        val json = frame.text()
        val session = PlayerSession.getPlayerFromChannelHandlerContext(ctx)

        val message = if (session != null) {
            gson.fromJson(json, IncomingPlayerMessage::class.java).apply {
                playerSession = session
            }
        } else {
            gson.fromJson(json, IncomingMessage::class.java)
        }

        out.add(message)
    }
}