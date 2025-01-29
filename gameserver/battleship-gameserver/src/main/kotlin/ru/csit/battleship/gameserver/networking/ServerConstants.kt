package ru.csit.battleship.gameserver.networking

object ServerConstants {

    object HandlerNames {
        const val TXT_WS_DECODER = "textWebsocketDecoder"
        const val TXT_WS_ENCODER = "textWebsocketEncoder"
        const val PING_PONG_HANDLER = "pingPongHandler"
        const val INIT_HANDLER = "initialHandler"
        const val EVENT_HANDLER = "eventHandler"
        const val DECODER = "decoder"
        const val AGGREGATOR = "aggregator"
        const val HANDLER = "handler"
        const val ENCODER = "encoder"
    }

    const val WEBSOCKET_PATH = "/game"

    const val DEFAULT_OBJECT_AGGREGATOR_CONTENT_LENGTH = 65536;
}