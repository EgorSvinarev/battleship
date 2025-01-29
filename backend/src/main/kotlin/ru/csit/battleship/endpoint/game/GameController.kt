package ru.csit.battleship.endpoint.game

import jakarta.validation.Valid
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.csit.battleship.domain.game.GameService
import ru.csit.battleship.domain.game.dto.CreateGameRequest
import ru.csit.battleship.endpoint.GamePath.GAME_CREATE_PATH

@RestController
class GameController(
    private val service: GameService,
) : GameApi {

    @PostMapping(GAME_CREATE_PATH, consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
    override fun create(@Valid @RequestBody request: CreateGameRequest) = service.create(request)
}