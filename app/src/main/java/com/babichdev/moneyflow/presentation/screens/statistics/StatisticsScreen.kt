package com.babichdev.moneyflow.presentation.screens.statistics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.babichdev.moneyflow.presentation.model.PeriodFilter
import com.babichdev.moneyflow.util.MoneyFormatter

@Composable
fun StatisticsScreen(
    viewModel: StatisticsViewModel
) {

    val statistics by viewModel.statistics.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Статистика",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            items(PeriodFilter.entries) { period ->

                FilterChip(
                    selected = period == statistics.selectedPeriod,
                    onClick = {
                        viewModel.setPeriod(period)
                    },
                    label = {
                        Text(period.title)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        StatCard(
            title = "Доходы",
            value = MoneyFormatter.format(statistics.income)
        )

        StatCard(
            title = "Расходы",
            value = MoneyFormatter.format(statistics.expense)
        )

        StatCard(
            title = "Баланс",
            value = MoneyFormatter.format(statistics.balance)
        )

        StatCard(
            title = "Операций",
            value = statistics.operationsCount.toString()
        )

        Text(
            text = "Расходы по категориям",
            style = MaterialTheme.typography.titleLarge
        )

        statistics.expenseByCategory.forEach { category ->

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = category.category,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Text(
                        text = "${category.amount.toInt()} ₽",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Composable
private fun StatCard(
    title: String,
    value: String
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = value,
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}