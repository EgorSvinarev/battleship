package ru.csit.battleship.endpoint.auth

import jakarta.validation.Valid
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.csit.battleship.domain.auth.AuthService
import ru.csit.battleship.domain.auth.dto.LoginRequest
import ru.csit.battleship.domain.auth.dto.RegisterRequest
import ru.csit.battleship.domain.auth.dto.TokensRequest
import ru.csit.battleship.endpoint.AuthPath.AUTH_LOGIN_PATH
import ru.csit.battleship.endpoint.AuthPath.AUTH_REFRESH_TOKENS_PATH
import ru.csit.battleship.endpoint.AuthPath.AUTH_REGISTER_PATH

@RestController
class AuthController(
    private val service: AuthService
) : AuthApi {

    @PostMapping(AUTH_LOGIN_PATH, consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
    override fun login(@Valid @RequestBody request: LoginRequest) = service.login(request)

    @PostMapping(AUTH_REGISTER_PATH, consumes = [APPLICATION_JSON_VALUE])
    override fun register(@Valid @RequestBody request: RegisterRequest) = service.register(request)

    @PostMapping(AUTH_REFRESH_TOKENS_PATH, consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
    override fun refreshTokens(@Valid @RequestBody request: TokensRequest) = service.refreshTokens(request)
}