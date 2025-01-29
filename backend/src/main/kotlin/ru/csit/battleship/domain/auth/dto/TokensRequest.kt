package ru.csit.battleship.domain.auth.dto

import io.swagger.v3.oas.annotations.media.Schema


@Schema(description = "Запрос на обновление токенов авторизации")
data class TokensRequest(

    override val accessToken: String,
    override val refreshToken: String
) : TokensDetails