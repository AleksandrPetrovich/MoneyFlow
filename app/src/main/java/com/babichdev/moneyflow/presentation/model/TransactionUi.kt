package com.babichdev.moneyflow.presentation.model

data class TransactionUi(
    val title: String,
    val category: String,
    val amount: Double,
    val isIncome: Boolean
)