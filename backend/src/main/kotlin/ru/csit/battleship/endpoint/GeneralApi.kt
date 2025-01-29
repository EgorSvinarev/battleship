package ru.csit.battleship.endpoint

import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.core.annotation.AliasFor
import ru.csit.battleship.domain.error.dto.Error

object ApiName {

    const val AUTH_TAG = "Аутентификация/авторизация"
    const val GAME_TAG = "Игровые сессии"
    const val USER_TAG = "Пользователь"
}

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@ApiResponse(responseCode = "200")
annotation class ApiSuccessfulResponse(

    @get:AliasFor(annotation = ApiResponse::class, attribute = "description")
    val description: String = "Успешный ответ",

    @get:AliasFor(annotation = ApiResponse::class, attribute = "content")
    val content: Array<Content> = [],
)

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@ApiResponses(
    value = [
        ApiResponse(
            responseCode = "400",
            description = "Некорректный запрос клиента",
            content = [Content(schema = Schema(implementation = Error::class))]
        ),
        ApiResponse(
            responseCode = "500",
            description = "Ошибка обработки запроса на сервере",
            content = [Content(schema = Schema(implementation = Error::class))]
        )
    ]
)
annotation class ApiErrorResponses