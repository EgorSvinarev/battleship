package ru.csit.battleship.domain.auth.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Ответ с токенами авторизации")
data class TokensResponse(

    override val accessToken: String,
    override val refreshToken: String,
) : TokensDetails