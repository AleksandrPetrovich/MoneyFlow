package com.babichdev.moneyflow.presentation.screens.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.babichdev.moneyflow.data.repository.TransactionRepository
import com.babichdev.moneyflow.presentation.model.TransactionUi
import com.babichdev.moneyflow.presentation.model.toUi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HistoryViewModel(
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
}