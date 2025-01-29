package ru.csit.battleship.gameserver.networking.adapter

import com.google.gson.Gson
import io.netty.channel.ChannelHandler.Sharable
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageEncoder
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame
import org.springframework.stereotype.Component
import ru.csit.battleship.gameserver.networking.message.AbstractMessage

@Sharable
@Component
class TextWebsocketEncoder(
    private val gson: Gson = Gson()
) : MessageToMessageEncoder<AbstractMessage>() {

    override fun encode(ctx: ChannelHandlerContext, message: AbstractMessage    , out: MutableList<Any>) {
        out.add(TextWebSocketFrame(gson.toJson(message)))
    }
}