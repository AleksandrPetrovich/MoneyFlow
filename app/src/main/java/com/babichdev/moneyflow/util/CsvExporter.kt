package com.babichdev.moneyflow.util

import com.babichdev.moneyflow.presentation.model.TransactionUi

object CsvExporter {

    fun export(transactions: List<TransactionUi>): String {

        val builder = StringBuilder()

        builder.appendLine("Date;Type;Category;Amount;Comment")

        transactions.forEach { transaction ->

            builder.appendLine(
                buildString {

                    append(DateFormatter.format(transaction.date))
                    append(";")

                    append(
                        if (transaction.isIncome)
                            "Income"
                        else
                            "Expense"
                    )

                    append(";")
                    append(transaction.category)
                    append(";")
                    append(transaction.amount)
                    append(";")
                    append(transaction.comment.replace(";", ","))

                }
            )
        }

        return builder.toString()
    }
}