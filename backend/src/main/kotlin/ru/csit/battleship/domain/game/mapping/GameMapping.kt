package ru.csit.battleship.domain.game.mapping

import org.springframework.dao.DataIntegrityViolationException
import ru.csit.battleship.domain.date.format
import ru.csit.battleship.domain.game.dto.CreateGameRequest
import ru.csit.battleship.domain.game.dto.GameDto
import ru.csit.battleship.domain.statistics.dto.StatisticsDto
import ru.csit.battleship.entity.game.Game
import ru.csit.battleship.entity.user.User

fun Game.toDto() = GameDto(
    userId = user.id,
    isWinner = isWinner,
    createdDate = createdDate.format()
)

fun Set<Game>.toStatistics(user: User): StatisticsDto {
    if (this.any { it.user.id != user.id })
        throw DataIntegrityViolationException("Ошибка в процессе извлечения игровой статистики.")

    val win = this.filter { it.isWinner }.size.toLong()
    val lost = this.size - win
    val winRate = if (this.isNotEmpty()) (win * 1.0) / this.size else 0.0

    return StatisticsDto(
        userId = user.id,
        total = this.size.toLong(),
        win = win,
        lost = lost,
        winRate = winRate
    )
}

fun CreateGameRequest.toEntity(user: User) = Game(
    user = user,
    isWinner = isWinner
)
