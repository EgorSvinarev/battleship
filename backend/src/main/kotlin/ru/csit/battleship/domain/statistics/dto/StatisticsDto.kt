package ru.csit.battleship.domain.statistics.dto

import io.swagger.v3.oas.annotations.media.Schema

/**
 * Статистика пользователя по сыгранным партиям
 *
 * @param userId Идентификатор пользователя
 * @param total Количество сыгранных партий
 * @param win Количество выигранных партий
 * @param lost Количество проигранных партий
 * @param winRate Соотношение выигранных партий к общему количеству
 */
data class StatisticsDto (

    @Schema(description = "Идентификатор пользователя", required = true, example = "1")
    val userId: Long,

    @Schema(description = "Количество сыгранных партий", required = true, example = "10")
    val total: Long,

    @Schema(description = "Количество выигранных партий", required = true, example = "5")
    val win: Long,

    @Schema(description = "Количество проигранных партий", required = true, example = "5")
    val lost: Long,

    @Schema(description = "Соотношение выигранных партий к общему количеству", required = true, example = "0.5")
    val winRate: Double
)