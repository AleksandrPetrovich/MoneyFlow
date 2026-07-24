package com.babichdev.moneyflow.data.repository

import com.babichdev.moneyflow.data.local.dao.TransactionDao
import com.babichdev.moneyflow.data.local.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

class TransactionRepository(
    private val dao: TransactionDao
) {

    fun getAllTransactions(): Flow<List<TransactionEntity>> {
        return dao.getAllTransactions()
    }

    suspend fun insertTransaction(
        transaction: TransactionEntity
    ) {
        dao.insertTransaction(transaction)
    }

    suspend fun deleteTransaction(
        transaction: TransactionEntity
    ) {
        dao.deleteTransaction(transaction)
    }

    suspend fun deleteTransaction(id: Long) {
        dao.deleteById(id)
    }
}