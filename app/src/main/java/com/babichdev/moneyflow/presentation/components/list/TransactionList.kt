package com.babichdev.moneyflow.presentation.components.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.babichdev.moneyflow.presentation.components.cards.TransactionItem
import com.babichdev.moneyflow.presentation.model.TransactionUi

@Composable
fun TransactionList(
    transactions: List<TransactionUi>,
    onDelete: (TransactionUi) -> Unit = {}
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = transactions,
            key = { it.id }
        ) { transaction ->

            TransactionItem(
                transaction = transaction
            )
        }
    }
}