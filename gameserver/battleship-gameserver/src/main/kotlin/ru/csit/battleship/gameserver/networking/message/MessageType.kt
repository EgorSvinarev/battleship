package ru.csit.battleship.gameserver.networking.message

enum class MessageType(val value: String) {

    AUTHENTICATION("auth"),
    BOARD_CONFIG("board_config"),
    JOIN("join"),
    ROOM_CREATE("room_create"),
    ROOM_START("room_start"),
    ROOM_CLOSE("room_close"),
    ROOM_WAIT_PLAYER_SHOT("room_wait_player_shoot"),
    ROOM_SWITCH_TURN("room_switch_turn"),
    UPDATE("update"),
    FAILURE("failure"),
    PLAYER_READY("player_ready"),
    PLAYER_SHOT("player_shot"),
    PLAYER_SHIPS_PLACED("player_ships_placed"),
    PLAYER_WIN("player_win"),
    PLAYER_LOSE("player_lose"),
}