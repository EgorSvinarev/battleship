package ru.csit.battleship.endpoint.user

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import ru.csit.battleship.domain.user.dto.UserDto
import ru.csit.battleship.domain.user.dto.UsersRequest
import ru.csit.battleship.endpoint.ApiErrorResponses
import ru.csit.battleship.endpoint.ApiName.USER_TAG
import ru.csit.battleship.endpoint.ApiSuccessfulResponse

@ApiErrorResponses
@SecurityRequirement(name = "BearerAuth")
interface UserApi {

    @Operation(
        tags = [USER_TAG],
        summary = "Получение информации о пользователе"
    )
    @ApiSuccessfulResponse
    fun getById(
        @Parameter(
            name = "userId",
            description = "Идентификатор пользователя"
        ) userId: Long
    ): UserDto

    @Operation(
        tags = [USER_TAG],
        summary = "Получение информации о всех пользователях"
    )
    @ApiSuccessfulResponse
    fun getAll(
        @RequestBody(
            description = "Запрос для извлечения пользователей",
            required = true
        ) request: UsersRequest
    ): List<UserDto>
}