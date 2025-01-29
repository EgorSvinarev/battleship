package ru.csit.battleship.domain.user.dto

import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import ru.csit.battleship.domain.game.dto.GameDto
import ru.csit.battleship.domain.validation.DefaultSize

/**
 * Данные о пользователе
 *
 * @param id Идентификатор пользователя
 * @param username Псевдоним пользователя
 * @param email Адрес электронной почты пользователя
 * @param gameHistory История игровых сессий пользователя
 */
interface UserDetails {

    @get:Schema(description = "Идентификатор пользователя", required = true, example = "1")
    val id: Long

    @get:Schema(description = "Псевдоним пользователя", required = true, example = "admin")
    @get:DefaultSize
    val username: String

    @get:Schema(
        description = "Адрес электронной почты пользователя",
        required = true, example = "admin@example.com"
    )
    @get:DefaultSize
    @get:Email
    val email: String

    @get:ArraySchema(schema = Schema(
        description = "Данные о состоявшейся игровой сессии",
        implementation = GameDto::class
    ))
    val gameHistory: List<GameDto>

    @get:Schema(description = "Ранг пользователя", required = true, example = "0")
    val xp: Long
}