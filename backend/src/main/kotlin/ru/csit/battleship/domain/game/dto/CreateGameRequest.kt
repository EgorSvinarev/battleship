package ru.csit.battleship.domain.game.dto

import io.swagger.v3.oas.annotations.media.Schema

/**
 * Запрос на создание записи об игровой сессии
 *
 * @param userId Идентификатор пользователя
 * @param isWinner Признак, показывающий, что игрок выиграл
 */
@Schema(description = "Запрос на создание записи об игровой сессии")
data class CreateGameRequest (

    @Schema(description = "Идентификатор пользователя", example = "1", required = true)
    val userId: Long,

    @Schema(description = "Признак, показывающий, что игрок выиграл", example = "true", required = true)
    val isWinner: Boolean
)