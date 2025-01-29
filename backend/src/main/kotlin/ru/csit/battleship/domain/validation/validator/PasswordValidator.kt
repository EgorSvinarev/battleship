package ru.csit.battleship.domain.validation.validator

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import ru.csit.battleship.domain.validation.Password
import java.util.regex.Pattern

class PasswordValidator : ConstraintValidator<Password, String> {

    override fun isValid(password: String, context: ConstraintValidatorContext): Boolean {
        if (!password.isValid()) {
            context.disableDefaultConstraintViolation()
            context.buildConstraintViolationWithTemplate(PASSWORD_INVALID_MESSAGE)
                .addConstraintViolation()
            return false
        }
        return true
    }

    private fun String.isValid(): Boolean {
        val pattern = Pattern.compile("[0-9a-zA-Z.,&%\$#@!_]{8,}\n")
        return pattern.matcher(this).matches()
    }

    companion object {
        private const val PASSWORD_INVALID_MESSAGE = "Пароль должен состоять только из латинских символов, цифр, и спец.символов(. , & % $ # @ ! _) и быть не короче 8 символов"
    }
}