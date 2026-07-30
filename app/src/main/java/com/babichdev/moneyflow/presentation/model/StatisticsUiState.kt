package com.babichdev.moneyflow.presentation.model

data class StatisticsUiState(
    val income: Double = 0.0,
    val expense: Double = 0.0,
    val balance: Double = 0.0,
    val operationsCount: Int = 0,
    val expenseByCategory: List<CategoryStatistic> = emptyList(),
    val selectedPeriod: PeriodFilter = PeriodFilter.ALL
)