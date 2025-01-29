package ru.csit.battleship.domain.page.dto

import io.swagger.v3.oas.annotations.media.Schema

/**
 * Данные сортировки по атрибуту
 *
 * @param sortingAttribute Название атрибута
 * @param isAsc Признак для сортировки по возрастанию
 */
@Schema(description = "Данные сортировки по атрибуту")
data class SortRequest (

    @Schema(description = "Название атрибута", required = true, example = "name")
    val sortingAttribute: String,

    @Schema(description = "Признак для сортировки по возрастанию", required = true, example = "true")
    val isAsc: Boolean
)