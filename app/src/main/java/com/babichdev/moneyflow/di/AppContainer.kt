package com.babichdev.moneyflow.di

import android.content.Context
import androidx.room.Room
import com.babichdev.moneyflow.data.local.database.MoneyFlowDatabase
import com.babichdev.moneyflow.data.repository.TransactionRepository

class AppContainer(context: Context) {

    private val database = Room.databaseBuilder(
        context,
        MoneyFlowDatabase::class.java,
        "moneyflow.db"
    ).build()

    val repository = TransactionRepository(
        database.transactionDao()
    )
}