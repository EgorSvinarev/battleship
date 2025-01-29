package ru.csit.battleship.domain.auth

import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.csit.battleship.domain.auth.dto.LoginRequest
import ru.csit.battleship.domain.auth.dto.RegisterRequest
import ru.csit.battleship.domain.auth.dto.TokensRequest
import ru.csit.battleship.domain.auth.dto.TokensResponse
import ru.csit.battleship.domain.auth.extension.passwordEquals
import ru.csit.battleship.domain.auth.jwt.JwtService
import ru.csit.battleship.domain.auth.mapping.toEntity
import ru.csit.battleship.entity.user.User
import ru.csit.battleship.exception.AuthenticationException
import ru.csit.battleship.repository.user.UserRepository

@Service
class AuthServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService,
) : AuthService, UserDetailsService {

    @Transactional(readOnly = true)
    override fun login(request: LoginRequest): TokensResponse {
        val storedUser = userRepository.findByEmail(request.email) ?: throw AuthenticationException()

        if (storedUser.passwordEquals(
                password = request.password,
                encoder = passwordEncoder)
        ) {
            return storedUser.authenticate()
        } else {
            throw AuthenticationException()
        }
    }

    @Transactional
    override fun register(request: RegisterRequest) {
        userRepository.save(request.toEntity(encoder = passwordEncoder))
    }

    @Transactional(readOnly = true)
    override fun refreshTokens(request: TokensRequest): TokensResponse {
        val refreshToken = request.refreshToken
        val username = jwtService.extractSubject(refreshToken)
        val storedUser = userRepository.findByUsername(username) ?: throw AuthenticationException()

        return if (refreshToken.isTokenValid()) storedUser.authenticate() else throw AuthenticationException()
    }

    @Transactional(readOnly = true)
    override fun loadUserByUsername(username: String) = userRepository.findByUsername(username)

    private fun User.authenticate() = TokensResponse(
        accessToken = jwtService.generateAccessToken(this),
        refreshToken = jwtService.generateRefreshToken(this)
    )

    private fun String.isTokenValid() = jwtService.validateToken(this)
}