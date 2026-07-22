package com.babichdev.moneyflow.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.babichdev.moneyflow.presentation.components.cards.BalanceCard
import com.babichdev.moneyflow.presentation.components.cards.TransactionItem

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    val transactions by viewModel.transactions.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            BalanceCard(
                balance = 125430.0,
                income = 154000.0,
                expense = 28570.0
            )
        }

        item {
            Text(
                text = "Последние операции",
                style = MaterialTheme.typography.titleLarge
            )
        }

        items(transactions) { transaction ->
            TransactionItem(transaction)
        }
    }
}