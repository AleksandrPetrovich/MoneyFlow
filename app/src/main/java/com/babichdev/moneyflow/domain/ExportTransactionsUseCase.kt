package com.babichdev.moneyflow.domain.usecase

import com.babichdev.moneyflow.data.repository.TransactionRepository
import com.babichdev.moneyflow.presentation.model.toUi
import com.babichdev.moneyflow.util.CsvExporter
import kotlinx.coroutines.flow.first

class ExportTransactionsUseCase(
    private val repository: TransactionRepository
) {

    suspend operator fun invoke(): String {

        val transactions = repository
            .getAllTransactions()
            .first()

        val uiTransactions = transactions.map {
            it.toUi()
        }

        return CsvExporter.export(uiTransactions)
    }
}