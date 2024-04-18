package com.id.angga.loginmodule.domain.usecase.validation

import com.id.angga.loginmodule.R
import com.id.angga.loginmodule.presentation.UiText

class ValidateTerms {
    fun execute(acceptedTerms : Boolean) : ValidationResult {
        if (!acceptedTerms) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.terms_not_valid)
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}