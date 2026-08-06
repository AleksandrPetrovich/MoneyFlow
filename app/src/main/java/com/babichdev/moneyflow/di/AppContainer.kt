package com.babichdev.moneyflow.di

import android.content.Context
import androidx.room.Room
import com.babichdev.moneyflow.data.local.database.MoneyFlowDatabase
import com.babichdev.moneyflow.data.preferences.SettingsRepository
import com.babichdev.moneyflow.data.repository.TransactionRepository
import com.babichdev.moneyflow.domain.usecase.ExportTransactionsUseCase

class AppContainer(context: Context) {

    private val database = Room.databaseBuilder(
        context,
        MoneyFlowDatabase::class.java,
        "moneyflow.db"
    ).build()

    val repository = TransactionRepository(
        database.transactionDao()
    )

    val exportTransactionsUseCase = ExportTransactionsUseCase(repository)

    val settingsRepository = SettingsRepository(
        context
    )
}