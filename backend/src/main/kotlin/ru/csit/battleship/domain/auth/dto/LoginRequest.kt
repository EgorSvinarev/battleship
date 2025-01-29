package ru.csit.battleship.domain.auth.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import ru.csit.battleship.domain.validation.DefaultSize

/**
 * Запрос для авторизации пользователя
 *
 * @param email Адрес электронной почты пользователя
 * @param password Пароль пользователя
 */
@Schema(description = "Запрос для авторизации пользователя")
data class LoginRequest(

    @Schema(
        description = "Адрес электронной почты пользователя",
        required = true, example = "admin@example.com"
    )
    @DefaultSize
    @Email
    val email: String,

    @Schema(description = "Пароль пользователя", required = true, example = "password")
    @DefaultSize
    val password: String,
)