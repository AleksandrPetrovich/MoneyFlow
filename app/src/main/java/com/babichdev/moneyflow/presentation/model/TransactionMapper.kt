package com.babichdev.moneyflow.presentation.model

import com.babichdev.moneyflow.data.local.entity.TransactionEntity

fun TransactionEntity.toUi(): TransactionUi {
    return TransactionUi(
        id = id,
        category = category,
        comment = comment,
        amount = amount,
        isIncome = isIncome,
        date = date
    )
}