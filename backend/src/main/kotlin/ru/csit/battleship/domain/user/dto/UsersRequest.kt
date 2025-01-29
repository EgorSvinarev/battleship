package ru.csit.battleship.domain.user.dto

import io.swagger.v3.oas.annotations.media.Schema
import ru.csit.battleship.domain.page.dto.PageRequest
import ru.csit.battleship.domain.page.dto.SortRequest

/**
 * Запрос на получение списка пользователей
 *
 * @param paging Данные постраничной разбивки
 */
@Schema(description = "Запрос на получение списка пользователей")
data class UsersRequest(

    @Schema(description = "Данные постраничной разбивки", required = true)
    val paging: PageRequest = PageRequest(),
)