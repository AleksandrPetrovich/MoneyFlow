package com.babichdev.moneyflow.presentation.screens.statistics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.babichdev.moneyflow.data.repository.TransactionRepository
import com.babichdev.moneyflow.presentation.model.CategoryStatistic
import com.babichdev.moneyflow.presentation.model.PeriodFilter
import com.babichdev.moneyflow.presentation.model.StatisticsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class StatisticsViewModel(
    repository: TransactionRepository
) : ViewModel() {

    private val selectedPeriod =
        MutableStateFlow(PeriodFilter.ALL)

    val statistics: StateFlow<StatisticsUiState> =
        combine(
            repository.getAllTransactions(),
            selectedPeriod
        ) { transactions, period ->

            val currentTime = System.currentTimeMillis()

            val filteredTransactions = when (period) {

                PeriodFilter.ALL -> transactions

                PeriodFilter.TODAY ->
                    transactions.filter {
                        currentTime - it.date < 24L * 60 * 60 * 1000
                    }

                PeriodFilter.WEEK ->
                    transactions.filter {
                        currentTime - it.date < 7L * 24 * 60 * 60 * 1000
                    }

                PeriodFilter.MONTH ->
                    transactions.filter {
                        currentTime - it.date < 30L * 24 * 60 * 60 * 1000
                    }

                PeriodFilter.YEAR ->
                    transactions.filter {
                        currentTime - it.date < 365L * 24 * 60 * 60 * 1000
                    }
            }

            val income = filteredTransactions
                .filter { it.isIncome }
                .sumOf { it.amount }

            val expense = filteredTransactions
                .filter { !it.isIncome }
                .sumOf { it.amount }

            val expenseByCategory = filteredTransactions
                .filter { !it.isIncome }
                .groupBy { it.category }
                .map { (category, list) ->
                    CategoryStatistic(
                        category = category,
                        amount = list.sumOf { it.amount }
                    )
                }
                .sortedByDescending { it.amount }

            StatisticsUiState(
                income = income,
                expense = expense,
                balance = income - expense,
                operationsCount = transactions.size,
                expenseByCategory = expenseByCategory,
                selectedPeriod = period
            )
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = StatisticsUiState()
            )

    fun setPeriod(period: PeriodFilter) {
        selectedPeriod.value = period

    }
}