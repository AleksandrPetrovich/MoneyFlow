package com.babichdev.moneyflow.presentation.screens.statistics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.babichdev.moneyflow.data.repository.TransactionRepository
import com.babichdev.moneyflow.presentation.model.CategoryStatistic
import com.babichdev.moneyflow.presentation.model.StatisticsUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class StatisticsViewModel(
    repository: TransactionRepository
) : ViewModel() {

    val statistics: StateFlow<StatisticsUiState> =
        repository
            .getAllTransactions()
            .map { transactions ->

                val income = transactions
                    .filter { it.isIncome }
                    .sumOf { it.amount }

                val expense = transactions
                    .filter { !it.isIncome }
                    .sumOf { it.amount }

                val expenseByCategory = transactions
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
                    expenseByCategory = expenseByCategory
                )
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = StatisticsUiState()
            )
}