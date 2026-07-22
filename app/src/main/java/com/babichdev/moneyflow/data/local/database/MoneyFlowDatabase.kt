package com.babichdev.moneyflow.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.babichdev.moneyflow.data.local.dao.TransactionDao
import com.babichdev.moneyflow.data.local.entity.TransactionEntity

@Database(
    entities = [TransactionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MoneyFlowDatabase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao
}