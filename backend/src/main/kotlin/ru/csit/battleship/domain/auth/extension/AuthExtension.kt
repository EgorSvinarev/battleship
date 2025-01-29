package ru.csit.battleship.domain.auth.extension

import io.jsonwebtoken.Claims
import org.springframework.security.crypto.password.PasswordEncoder
import ru.csit.battleship.domain.auth.dto.TokensResponse
import ru.csit.battleship.entity.user.User

fun User.passwordEquals(password: String, encoder: PasswordEncoder) =
    encoder.matches(password, passwordHash)
