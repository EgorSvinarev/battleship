package ru.csit.battleship.domain.auth.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import mu.KotlinLogging.logger
import org.springframework.stereotype.Service
import ru.csit.battleship.config.security.TokensProperties
import ru.csit.battleship.entity.user.User
import java.time.Instant
import java.time.temporal.ChronoUnit.MINUTES
import java.util.*
import java.util.function.Function


@Service
class JwtServiceImpl(
    private val tokenProperties: TokensProperties
) : JwtService {

    override fun generateAccessToken(user: User): String {
        val now = Instant.now()
        val accessExpiration = Date.from(now.plus(accessTokenExpireInMinutes, MINUTES))

        return Jwts.builder().claims(mapOf("userId" to user.id)).subject(user.username).expiration(accessExpiration).signWith(secretKey).compact()
    }

    override fun generateRefreshToken(user: User): String {
        val now = Instant.now()
        val refreshExpiration = Date.from(now.plus(refreshTokenExpireInMinutes, MINUTES))

        return Jwts.builder().claims(mapOf("userId" to user.id)).subject(user.username).expiration(refreshExpiration).signWith(secretKey).compact()
    }

    override fun validateToken(token: String): Boolean {
        runCatching {
            Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token)
        }.onFailure {
            log.info("Token is invalid.", it)
            return false
        }
        return true
    }

    override fun extractSubject(token: String): String {
        return extractClaim(token, Claims::getSubject)
    }

    fun <T> extractClaim(token: String, claimsResolver: Function<Claims, T>): T {
        val claims = extractAllClaims(token)
        return claimsResolver.apply(claims)
    }

    private fun extractAllClaims(token: String): Claims {
        return Jwts.parser().setSigningKey(secretKey).build().parseSignedClaims(token).body
    }

    private val secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(tokenProperties.signing.secretKey))
    private val accessTokenExpireInMinutes = tokenProperties.accessToken.expireInMinutes
    private val refreshTokenExpireInMinutes = tokenProperties.refreshToken.expireInMinutes

    companion object {
        private val log = logger {}
    }
}