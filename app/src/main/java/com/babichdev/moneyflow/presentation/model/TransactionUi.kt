package com.babichdev.moneyflow.presentation.model

data class TransactionUi(
    val category: String,
    val comment: String,
    val amount: Double,
    val isIncome: Boolean,
    val date: Long
)