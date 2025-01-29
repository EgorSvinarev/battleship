package ru.csit.battleship.domain.auth.mapping

import org.springframework.security.crypto.password.PasswordEncoder
import ru.csit.battleship.domain.auth.dto.RegisterRequest
import ru.csit.battleship.entity.user.User

fun RegisterRequest.toEntity(encoder: PasswordEncoder) = User(
    username = username,
    email = email,
    passwordHash = encoder.encode(password),
    xp = 0,
)