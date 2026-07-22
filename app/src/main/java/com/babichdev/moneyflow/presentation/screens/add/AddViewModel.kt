package com.babichdev.moneyflow.presentation.screens.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.babichdev.moneyflow.data.local.entity.TransactionEntity
import com.babichdev.moneyflow.data.repository.TransactionRepository
import kotlinx.coroutines.launch

class AddViewModel(
    private val repository: TransactionRepository
) : ViewModel() {

    fun addTransaction(
        amount: String,
        category: String,
        comment: String,
        isIncome: Boolean
    ) {
        val amountValue = amount.toDoubleOrNull() ?: return

        viewModelScope.launch {
            repository.insertTransaction(
                TransactionEntity(
                    amount = amountValue,
                    category = category,
                    comment = comment,
                    isIncome = isIncome,
                    date = System.currentTimeMillis()
                )
            )
        }
    }
}