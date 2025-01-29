package ru.csit.battleship.endpoint.auth

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.parameters.RequestBody
import ru.csit.battleship.domain.auth.dto.LoginRequest
import ru.csit.battleship.domain.auth.dto.RegisterRequest
import ru.csit.battleship.domain.auth.dto.TokensRequest
import ru.csit.battleship.domain.auth.dto.TokensResponse
import ru.csit.battleship.endpoint.ApiErrorResponses
import ru.csit.battleship.endpoint.ApiName.AUTH_TAG
import ru.csit.battleship.endpoint.ApiSuccessfulResponse

@ApiErrorResponses
interface AuthApi {

    @Operation(
        tags = [AUTH_TAG],
        summary = "Аутентификация пользователя и получение access и refresh токенов."
    )
    @ApiSuccessfulResponse
    fun login(
        @RequestBody(
            description = "Запрос для авторизации пользователя",
            required = true
        ) request: LoginRequest
    ): TokensResponse

    @Operation(
        tags = [AUTH_TAG],
        summary = "Регистрация пользователя на платформе."
    )
    @ApiSuccessfulResponse
    fun register(
        @RequestBody(
            description = "Запрос для регистрации пользователя",
            required = true
        ) request: RegisterRequest
    )

    @Operation(
        tags = [AUTH_TAG],
        summary = "Обновление access-токена."
    )
    @ApiSuccessfulResponse
    fun refreshTokens(
        @RequestBody(
            description = "Запрос на обновление токенов",
            required = true
        ) request: TokensRequest
    ): TokensResponse
}