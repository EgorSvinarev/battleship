package ru.csit.battleship.endpoint

import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import jakarta.validation.ConstraintViolationException
import mu.KotlinLogging.logger
import org.springframework.core.NestedExceptionUtils
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.DuplicateKeyException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus.*
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.ServletRequestBindingException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.NoHandlerFoundException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import ru.csit.battleship.domain.error.dto.Error
import ru.csit.battleship.endpoint.ErrorTitle.title
import ru.csit.battleship.exception.AuthenticationException
import ru.csit.battleship.exception.EntityNotFoundException

@RestControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler
    @ResponseStatus(BAD_REQUEST)
    fun handle(e: ConstraintViolationException): Error {
        val message = e.constraintViolations.firstOrNull()?.message ?: e.message
        return e.toErrorResponse(message)
    }

    @ExceptionHandler
    @ResponseStatus(UNAUTHORIZED)
    fun handle(e: AuthenticationException) = e.toErrorResponse(e.message)

    @ExceptionHandler
    @ResponseStatus(NOT_FOUND)
    fun handle(e: EntityNotFoundException) = e.toErrorResponse(e.message)

    @ExceptionHandler
    @ResponseStatus(BAD_REQUEST)
    fun hande(e: DuplicateKeyException): Error {
        log.error(DUPLICATE_KEY_ERROR, e)
        return e.toErrorResponse()
    }

    @ExceptionHandler
    @ResponseStatus(BAD_REQUEST)
    fun hande(e: DataIntegrityViolationException): Error {
        log.error(DATA_INTEGRITY_VIOLATION_ERROR, e)
        return e.toErrorResponse()
    }

    @ExceptionHandler
    fun handle(e: Exception): Error {
        log.error(INTERNAL_ERROR, e)
        return e.toErrorResponse()
    }

    public override fun handleServletRequestBindingException(
        e: ServletRequestBindingException, headers: HttpHeaders, status: HttpStatusCode, request: WebRequest
    ): ResponseEntity<Any> = e.toErrorResponse(e.message).toResponseEntity(status)

    public override fun handleHttpMessageNotReadable(
        e: HttpMessageNotReadableException, headers: HttpHeaders, status: HttpStatusCode, request: WebRequest
    ): ResponseEntity<Any> {
        val message = when (val cause = e.cause) {
            is InvalidFormatException -> "Некорректный формат значения ${cause.fieldPath()}"
            is MismatchedInputException -> "Отсутствует обязательное значение ${cause.fieldPath()}"
            else -> e.message
        }
        return e.toErrorResponse(message).toResponseEntity(status)
    }

    public override fun handleMethodArgumentNotValid(
        e: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatusCode, request: WebRequest
    ): ResponseEntity<Any> {
        val message = e.bindingResult.allErrors.firstOrNull()?.defaultMessage ?: e.message
        log.error { e.toString() }
        return e.toErrorResponse(message).toResponseEntity(status)
    }

    public override fun handleNoHandlerFoundException(
        e: NoHandlerFoundException, headers: HttpHeaders, status: HttpStatusCode, request: WebRequest
    ): ResponseEntity<Any> {
        val message = "Не найден обработчик для запроса: [${e.httpMethod}] ${e.requestURL}"
        return e.toErrorResponse(message).toResponseEntity(status)
    }

    public override fun handleExceptionInternal(
        e: Exception, body: Any?, headers: HttpHeaders, status: HttpStatusCode, request: WebRequest
    ): ResponseEntity<Any> {
        log.error(INTERNAL_ERROR, e)
        return e.toErrorResponse().toResponseEntity(status)
    }

    private fun JsonMappingException.fieldPath() = path.joinToString(separator = ".") { reference ->
        with(reference) {
            when {
                fieldName != null -> fieldName
                index >= 0 -> "[$index]"
                else -> "?"
            }
        }
    }

    private fun Exception.toErrorResponse(message: String? = null) =
        NestedExceptionUtils.getMostSpecificCause(this).let { cause ->
            Error(
                title = title(),
                type = cause::class.java.simpleName,
                message = message ?: cause.message,
            )
        }


    fun Error.toResponseEntity(status: HttpStatusCode) = ResponseEntity<Any>(this, status)

    companion object {
        private val log = logger {}

        private const val DATA_INTEGRITY_VIOLATION_ERROR = "Ошибка доступа к данным."
        private const val DUPLICATE_KEY_ERROR = "Ключ с таким значением уже существует."
        private const val INTERNAL_ERROR = "Внутренняя ошибка."
    }
}