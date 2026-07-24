package com.babichdev.moneyflow.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.babichdev.moneyflow.data.local.entity.TransactionEntity
import com.babichdev.moneyflow.data.repository.TransactionRepository
import com.babichdev.moneyflow.presentation.model.BalanceUi
import com.babichdev.moneyflow.presentation.model.TransactionUi
import com.babichdev.moneyflow.presentation.model.toUi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: TransactionRepository
) : ViewModel() {

    val transactions: StateFlow<List<TransactionUi>> =
        repository
            .getAllTransactions()
            .map { entities ->
                entities.map { it.toUi() }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    val balance =
        transactions
            .map { list ->

                val income = list
                    .filter { it.isIncome }
                    .sumOf { it.amount }

                val expense = list
                    .filterNot { it.isIncome }
                    .sumOf { it.amount }

                BalanceUi(
                    balance = income - expense,
                    income = income,
                    expense = expense
                )
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = BalanceUi()
            )

    fun deleteTransaction(transaction: TransactionEntity) {
        viewModelScope.launch {
            repository.deleteTransaction(transaction)
        }
    }

    fun deleteTransaction(id: Long) {
        viewModelScope.launch {
            repository.deleteTransaction(id)
        }
    }
}