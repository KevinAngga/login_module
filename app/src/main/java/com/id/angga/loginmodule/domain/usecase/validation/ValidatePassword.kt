package com.id.angga.loginmodule.domain.usecase.validation

import com.id.angga.loginmodule.R
import com.id.angga.loginmodule.presentation.UiText


class ValidatePassword {
    fun execute(password : String) : ValidationResult {
        if (password.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.password_length_error)
            )
        }

        val containLetterAndDigits = password.any { it.isDigit() } &&
                password.any { it.isLetter() }

        if (!containLetterAndDigits) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.password_not_valid)
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}