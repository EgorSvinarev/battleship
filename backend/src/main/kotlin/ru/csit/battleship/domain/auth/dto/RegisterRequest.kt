package ru.csit.battleship.domain.auth.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size
import ru.csit.battleship.domain.validation.DefaultSize
import ru.csit.battleship.domain.validation.Password

/**
 * Запрос для регистрации пользователя
 *
 * @param username Псевдоним пользователя
 * @param email Адрес электронной почты пользователя
 * @param password Пароль пользоватея
 */
@Schema(description = "Запрос для регистрации пользователя")
data class RegisterRequest(

    @Schema(description = "Псевдоним пользователя", required = true)
    @Size(min = 10, max = 20, message = "Длина строки должна быть в диапазоне 10..20 символов")
    val username: String,

    @Schema(description = "Адрес электронной почты пользователя", required = true)
    @DefaultSize
    @Email
    val email: String,

    @Schema(description = "Пароль пользователя", required = true)
    @DefaultSize
    @Password
    val password: String,
)