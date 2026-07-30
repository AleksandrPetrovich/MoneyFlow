package com.babichdev.moneyflow.presentation.screens.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.babichdev.moneyflow.data.local.entity.TransactionEntity
import com.babichdev.moneyflow.data.repository.TransactionRepository
import com.babichdev.moneyflow.presentation.model.Categories
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AddViewModel(
    private val repository: TransactionRepository,
    private val transactionId: Long
) : ViewModel() {

    init {

        if (isEditMode) {

            viewModelScope.launch {

                val entity = repository.getTransactionById(transactionId)

                entity?.let {

                    _amount.value = it.amount.toString()
                    _category.value = it.category
                    _comment.value = it.comment
                    _isIncome.value = it.isIncome
                    _date.value = it.date

                }
            }
        }
    }

    private val _amount = MutableStateFlow("")
    val amount: StateFlow<String> = _amount.asStateFlow()

    private val _category = MutableStateFlow("")
    val category: StateFlow<String> = _category.asStateFlow()

    private val _comment = MutableStateFlow("")
    val comment: StateFlow<String> = _comment.asStateFlow()

    private val _isIncome = MutableStateFlow(false)
    val isIncome: StateFlow<Boolean> = _isIncome.asStateFlow()

    private val _date = MutableStateFlow(System.currentTimeMillis())
    val date: StateFlow<Long> = _date.asStateFlow()

    val canSave: StateFlow<Boolean> =
        combine(
            amount,
            category
        ) { amount, category ->

            amount.toDoubleOrNull() != null &&
                    amount.toDoubleOrNull()!! > 0 &&
                    category.isNotBlank()

        }.stateIn(
            scope = viewModelScope,
            started = kotlinx.coroutines.flow.SharingStarted.Eagerly,
            initialValue = false
        )

    fun onAmountChanged(value: String) {
        _amount.value = value
    }

    fun onCategoryChanged(value: String) {
        _category.value = value
    }

    fun onCommentChanged(value: String) {
        _comment.value = value
    }

    fun onIncomeChanged(value: Boolean) {

        _isIncome.value = value

        _category.value =
            if (value) {
                Categories.income.first().title
            } else {
                Categories.expense.first().title
            }
    }

    fun onDateChanged(value: Long) {
        _date.value = value
    }

    val isEditMode: Boolean
        get() = transactionId != -1L

    fun saveTransaction() {
        val amountValue = _amount.value.toDoubleOrNull() ?: return

        viewModelScope.launch {

            if (isEditMode) {

                repository.updateTransaction(
                    TransactionEntity(
                        id = transactionId,
                        amount = amountValue,
                        category = _category.value,
                        comment = _comment.value,
                        isIncome = _isIncome.value,
                        date = _date.value
                    )
                )

            } else {

                repository.insertTransaction(
                    TransactionEntity(
                        amount = amountValue,
                        category = _category.value,
                        comment = _comment.value,
                        isIncome = _isIncome.value,
                        date = _date.value
                    )
                )

            }
        }
    }

    fun deleteTransaction() {

        if (!isEditMode) return

        viewModelScope.launch {
            repository.deleteTransaction(transactionId)
        }
    }
}