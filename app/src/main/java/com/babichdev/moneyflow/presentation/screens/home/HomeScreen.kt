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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.babichdev.moneyflow.R
import com.babichdev.moneyflow.presentation.components.cards.BalanceCard
import com.babichdev.moneyflow.presentation.components.cards.TransactionItem
import com.babichdev.moneyflow.presentation.model.Currency
import kotlinx.coroutines.flow.Flow

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    currency: Flow<Currency>,
    onEditTransaction: (Long) -> Unit = {}
) {
    val transactions by viewModel.transactions.collectAsStateWithLifecycle()

    val balance by viewModel.balance.collectAsStateWithLifecycle()

    val currentCurrency by currency.collectAsStateWithLifecycle(
        initialValue = Currency.RUB
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            BalanceCard(
                balance = balance.balance,
                income = balance.income,
                expense = balance.expense,
                currency = currentCurrency
            )
        }

        item {
            Text(
                text = stringResource(R.string.recent_transactions),
                style = MaterialTheme.typography.titleLarge
            )
        }

        items(transactions) { transaction ->

            TransactionItem(
                transaction = transaction,
                currency = currentCurrency,
                onLongClick = {
                    onEditTransaction(transaction.id)
                }
            )
        }
    }
}