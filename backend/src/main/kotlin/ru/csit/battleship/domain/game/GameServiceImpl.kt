package ru.csit.battleship.domain.game

import org.springframework.stereotype.Service
import ru.csit.battleship.domain.game.dto.CreateGameRequest
import ru.csit.battleship.domain.game.dto.GameDto
import ru.csit.battleship.domain.game.mapping.toDto
import ru.csit.battleship.domain.game.mapping.toEntity
import ru.csit.battleship.repository.game.GameRepository
import ru.csit.battleship.repository.user.UserRepository

@Service
class GameServiceImpl(
    private val repository: GameRepository,
    private val userRepository: UserRepository
) : GameService {

    override fun create(request: CreateGameRequest): GameDto {
        val storedUser = userRepository.findByIdOrElseThrow(request.userId)
        val game = repository.save(request.toEntity(storedUser)).toDto()

        storedUser.xp += if (request.isWinner) 10 else -10
        userRepository.save(storedUser)
        return game
    }

}