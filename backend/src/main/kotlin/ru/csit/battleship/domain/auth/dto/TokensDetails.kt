package ru.csit.battleship.domain.auth.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty

/**
 * Ответ с токенами авторизации
 *
 * @param accessToken Access-токен
 * @param refreshToken Refresh-токен
 */
interface TokensDetails {

    @get:Schema(description = "Access-токен", required = true)
    @get:NotEmpty
    val accessToken: String

    @get:Schema(description = "Refresh-токен", required = true)
    @get:NotEmpty
    val refreshToken: String
}