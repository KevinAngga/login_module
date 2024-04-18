package com.id.angga.loginmodule.domain.usecase.validation

import com.id.angga.loginmodule.presentation.UiText

data class ValidationResult (
    val successful : Boolean,
    val errorMessage : UiText? = null
)