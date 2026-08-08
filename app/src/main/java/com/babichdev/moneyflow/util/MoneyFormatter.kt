package com.babichdev.moneyflow.util

import com.babichdev.moneyflow.presentation.model.Currency
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

object MoneyFormatter {

    private val formatter = DecimalFormat(
        "#,##0.00",
        DecimalFormatSymbols(Locale.US)
    )

    fun format(
        amount: Double,
        currency: Currency
    ): String {

        return "${formatter.format(amount)} ${currency.symbol}"

    }
}