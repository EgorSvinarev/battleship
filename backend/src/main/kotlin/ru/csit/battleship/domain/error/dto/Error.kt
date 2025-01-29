package ru.csit.battleship.domain.error.dto

import io.swagger.v3.oas.annotations.media.Schema

/**
 * Ответ при ошибке выполения
 *
 * @param title Краткое описание ошибки
 * @param type Тип ошибки (наименование класса исключения)
 * @param message Сообщение ошибки
 */
@Schema(description = "Ответ при ошибке выполения")
data class Error(

    @Schema(description = "Краткое описание ошибки", example = "Ошибка валидации данных запроса", required = true)
    val title: String,

    @Schema(description = "Тип ошибки (наименование класса исключения)", example = "MethodArgumentNotValidException")
    val type: String,

    @Schema(description = "Сообщение ошибки", example = "Отсутствует параметр запроса")
    val message: String? = null,
)