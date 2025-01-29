package ru.csit.battleship.endpoint.game

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import ru.csit.battleship.domain.game.dto.CreateGameRequest
import ru.csit.battleship.domain.game.dto.GameDto
import ru.csit.battleship.endpoint.ApiErrorResponses
import ru.csit.battleship.endpoint.ApiName.GAME_TAG
import ru.csit.battleship.endpoint.ApiSuccessfulResponse

@ApiErrorResponses
@SecurityRequirement(name = "BearerAuth")
interface GameApi {

    @Operation(
        tags = [GAME_TAG],
        summary = "Создание новой игры"
    )
    @ApiSuccessfulResponse
    fun create(
        @RequestBody(
            description = "Данные для создания записи об игровой сессии",
            required = true
        ) request: CreateGameRequest
    ): GameDto
}