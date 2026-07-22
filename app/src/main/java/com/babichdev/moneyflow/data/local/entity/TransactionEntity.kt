package com.babichdev.moneyflow.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val amount: Double,

    val category: String,

    val comment: String,

    val isIncome: Boolean,

    val date: Long
)