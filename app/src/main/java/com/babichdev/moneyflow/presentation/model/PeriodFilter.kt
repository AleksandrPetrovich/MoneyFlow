package com.babichdev.moneyflow.presentation.model

enum class PeriodFilter(
    val title: String
) {
    TODAY("Сегодня"),
    WEEK("Неделя"),
    MONTH("Месяц"),
    YEAR("Год"),
    ALL("Всё время")
}