package com.babichdev.moneyflow.util

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

object MoneyFormatter {

    private val formatter = DecimalFormat(
        "#,###",
        DecimalFormatSymbols().apply {
            groupingSeparator = ' '
        }
    )

    fun format(amount: Double): String {
        return formatter.format(amount) + " ₽"
    }
}