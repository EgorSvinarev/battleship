package ru.csit.battleship.domain.game.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.time.Instant

/**
 * Данные о состоявшейся игровой сессии
 *
 * @param userId Идентификатор пользователя
 * @param isWinner Признак победы пользователя в партии
 * @param createdDate Дата проведения партии
 */
@Schema(description = "")
data class GameDto(

    @Schema(description = "Идентификатор пользователя", required = true, example = "1")
    val userId: Long,

    @Schema(description = "Признак победы пользователя в партии", required = true, example = "true")
    val isWinner: Boolean,

    @Schema(description = "Дата проведения партии", required = true, example = "2024-04-29")
    val createdDate: String
)