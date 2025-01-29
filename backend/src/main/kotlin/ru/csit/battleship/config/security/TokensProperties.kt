package ru.csit.battleship.config.security

import org.springframework.boot.context.properties.ConfigurationProperties
import javax.crypto.SecretKey

@ConfigurationProperties("app.jwt")
class TokensProperties(
    val signing: Signing,
    val accessToken: AccessToken,
    val refreshToken: RefreshToken,
) {

    data class Signing(
        val secretKey: String
    )

    data class AccessToken(
        val expireInMinutes: Long
    )

    data class RefreshToken(
        val expireInMinutes: Long
    )
}