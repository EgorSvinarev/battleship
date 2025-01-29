package ru.csit.battleship.domain.auth

import ru.csit.battleship.domain.auth.dto.LoginRequest
import ru.csit.battleship.domain.auth.dto.RegisterRequest
import ru.csit.battleship.domain.auth.dto.TokensRequest
import ru.csit.battleship.domain.auth.dto.TokensResponse

interface AuthService {

    fun login(request: LoginRequest): TokensResponse

    fun register(request: RegisterRequest)

    fun refreshTokens(request: TokensRequest): TokensResponse
}