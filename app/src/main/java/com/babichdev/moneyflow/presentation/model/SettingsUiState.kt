package com.babichdev.moneyflow.presentation.model

data class SettingsUiState(
    val currency: Currency = Currency.RUB,
    val darkTheme: Boolean = false
)