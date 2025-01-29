package ru.csit.battleship.domain.validation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import jakarta.validation.constraints.Size

import kotlin.annotation.AnnotationTarget.*
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.reflect.KClass

@Size(max = 255)
@Target(
    FIELD,
    PROPERTY_GETTER,
    VALUE_PARAMETER
)
@Retention(RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [])
annotation class DefaultSize(

    val message: String = "Длина строки повышает допустимые 255 символов",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
)
