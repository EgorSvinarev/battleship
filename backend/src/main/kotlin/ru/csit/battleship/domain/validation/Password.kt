package ru.csit.battleship.domain.validation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import ru.csit.battleship.domain.validation.validator.PasswordValidator
import kotlin.annotation.AnnotationTarget.*
import kotlin.reflect.KClass

@Target(
    FIELD,
    PROPERTY_GETTER,
    VALUE_PARAMETER
)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [PasswordValidator::class])
annotation class Password(

    val message: String = "Некорректный формат пароля",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
)
