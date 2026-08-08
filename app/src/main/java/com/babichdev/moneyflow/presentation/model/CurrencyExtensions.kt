package com.babichdev.moneyflow.presentation.model

import androidx.annotation.StringRes
import com.babichdev.moneyflow.R

@get:StringRes
val Currency.displayNameRes: Int
    get() = when (this) {
        Currency.RUB -> R.string.currency_rub
        Currency.USD -> R.string.currency_usd
        Currency.EUR -> R.string.currency_eur
    }