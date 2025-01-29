package ru.csit.battleship.endpoint

import jakarta.validation.ConstraintViolationException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.DuplicateKeyException
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.servlet.NoHandlerFoundException
import ru.csit.battleship.exception.AuthenticationException

internal object ErrorTitle {

    private const val DEFAULT_ERROR_TITLE = "Внутренняя ошибка обработки"

    private val TITLED_ERRORS = mapOf(
        AuthenticationException::class.java to "Ошибка аутентификации/авторизации пользователя",
        ConstraintViolationException::class.java to "Ошибка валидации параметров запроса",
        HttpMessageNotReadableException::class.java to "Ошибка чтения данных запроса",
        MethodArgumentNotValidException::class.java to "Ошибка валидации данных запроса",
        NoHandlerFoundException::class.java to "Неизвестный запрос",
        DataIntegrityViolationException::class.java to "Ошибка валидации запроса в базе данных",
        DuplicateKeyException::class.java to "Ключ с таким значением уже существует"
    )

    fun Exception.title(): String = TITLED_ERRORS.getOrDefault(this::class.java, DEFAULT_ERROR_TITLE)
}