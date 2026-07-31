package com.babichdev.moneyflow.presentation.model

import com.babichdev.moneyflow.R

enum class PeriodFilter(
    val titleRes: Int
) {
    TODAY(R.string.today),
    WEEK(R.string.week),
    MONTH(R.string.month),
    YEAR(R.string.year),
    ALL(R.string.all_time)
}