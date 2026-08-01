package com.babichdev.moneyflow.presentation.screens.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.babichdev.moneyflow.R
import com.babichdev.moneyflow.presentation.components.cards.TransactionItem
import com.babichdev.moneyflow.presentation.components.common.EmptyState

@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel
) {

    var searchText by remember { mutableStateOf("") }

    val transactions by viewModel.transactions.collectAsStateWithLifecycle()

    val filteredTransactions = transactions.filter { transaction ->

        if (searchText.isBlank()) {
            true
        } else {
            transaction.category.contains(searchText, ignoreCase = true) ||
                    transaction.comment.contains(searchText, ignoreCase = true)
        }
    }

    if (filteredTransactions.isEmpty()) {
        EmptyState(
            icon = Icons.Outlined.ReceiptLong,
            title = stringResource(R.string.empty_history_title),
            message = stringResource(R.string.empty_history_message)
        )
        return
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            Text(
                text = stringResource(R.string.transaction_history),
                style = MaterialTheme.typography.headlineSmall
            )
        }

        item {
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(stringResource(R.string.search))
                }
            )
        }

        items(filteredTransactions) { transaction ->
            TransactionItem(transaction)
        }
    }
}