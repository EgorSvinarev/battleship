package ru.csit.battleship.gameserver.dto.error

/**
 * Ответ при ошибке исполнения
 *
 * @param message Сообщение ошибки
 */
data class Error (

    val message: String
)