package com.id.angga.loginmodule.domain.usecase.validation

import com.id.angga.loginmodule.R
import com.id.angga.loginmodule.presentation.UiText

class ValidateRepeatedPassword {
    fun execute(password : String, repeatedPassword : String) : ValidationResult {
        if (password != repeatedPassword) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.re_type_not_valid)
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}