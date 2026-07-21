package com.babichdev.moneyflow.presentation.model

val fakeTransactions = listOf(
    TransactionUi(
        title = "Зарплата",
        category = "Работа",
        amount = 75000.0,
        isIncome = true
    ),
    TransactionUi(
        title = "Продукты",
        category = "Еда",
        amount = 2350.0,
        isIncome = false
    ),
    TransactionUi(
        title = "Кофе",
        amount = 450.0,
        category = "Кафе",
        isIncome = false
    )
)