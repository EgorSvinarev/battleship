package ru.csit.battleship.domain.game

import ru.csit.battleship.domain.game.dto.CreateGameRequest
import ru.csit.battleship.domain.game.dto.GameDto

interface GameService {

    fun create(request: CreateGameRequest): GameDto
}