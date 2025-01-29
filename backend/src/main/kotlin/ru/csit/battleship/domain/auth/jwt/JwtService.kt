package ru.csit.battleship.domain.auth.jwt

import ru.csit.battleship.config.security.TokensProperties
import ru.csit.battleship.entity.user.User

interface JwtService {

    fun generateAccessToken(user: User): String

    fun generateRefreshToken(user: User): String

    fun validateToken(token: String): Boolean

    fun extractSubject(token: String): String
}