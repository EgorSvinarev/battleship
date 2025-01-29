package ru.csit.battleship.domain.user.dto

import io.swagger.v3.oas.annotations.media.Schema
import ru.csit.battleship.domain.game.dto.GameDto
import ru.csit.battleship.domain.statistics.dto.StatisticsDto
import ru.csit.battleship.domain.user.dto.UserDetails

@Schema(description = "Данные о пользователе")
class UserDto(

    override val id: Long,
    override val username: String,
    override val email: String,
    override val gameHistory: List<GameDto> = listOf(),

    @Schema(description = "Статистика пользователя по сыгранным партиям", required = true)
    val stats: StatisticsDto,
    override val xp: Long,
) : UserDetails