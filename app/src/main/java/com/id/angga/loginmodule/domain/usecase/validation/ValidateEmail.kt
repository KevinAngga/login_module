package com.id.angga.loginmodule.domain.usecase.validation

import android.util.Patterns
import com.id.angga.loginmodule.R
import com.id.angga.loginmodule.presentation.UiText

class ValidateEmail {
    fun execute(email : String) : ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.email_cant_blank)
            )
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.email_not_valid)
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}