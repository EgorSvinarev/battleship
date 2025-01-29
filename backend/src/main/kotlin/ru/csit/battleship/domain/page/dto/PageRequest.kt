package ru.csit.battleship.domain.page.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min

/**
 * Данные постраничной разбивки
 *
 * @param offset Номер запрашиваемой страницы
 * @param limit Количество записей на странице
 */
@Schema(description = "Данные постраничной разбивки")
data class PageRequest(

    @Schema(description = "Количество записей на странице", required = true, example = "1")
    @Min(1)
    val offset: Int = 1,

    @Schema(description = "Номер запрашиваемой страницы", required = true, example = "1")
    @Min(1)
    @Max(100)
    val limit: Int = 10,
)