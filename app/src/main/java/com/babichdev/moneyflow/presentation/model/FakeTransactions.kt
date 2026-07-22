package com.babichdev.moneyflow.presentation.model

val fakeTransactions = listOf(

    TransactionUi(
        category = "Работа",
        comment = "Зарплата",
        amount = 75000.0,
        isIncome = true,
        date = System.currentTimeMillis()
    ),

    TransactionUi(
        category = "Еда",
        comment = "Продукты",
        amount = 2350.0,
        isIncome = false,
        date = System.currentTimeMillis()
    ),

    TransactionUi(
        category = "Кафе",
        comment = "Кофе",
        amount = 450.0,
        isIncome = false,
        date = System.currentTimeMillis()
    )
)