package com.babichdev.moneyflow.presentation.screens.statistics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.babichdev.moneyflow.R
import com.babichdev.moneyflow.presentation.components.common.EmptyState
import com.babichdev.moneyflow.presentation.model.Categories
import com.babichdev.moneyflow.presentation.model.PeriodFilter
import com.babichdev.moneyflow.ui.theme.ExpenseColor
import com.babichdev.moneyflow.ui.theme.IncomeColor
import com.babichdev.moneyflow.util.MoneyFormatter

@Composable
fun StatisticsScreen(
    viewModel: StatisticsViewModel
) {

    val statistics by viewModel.statistics.collectAsStateWithLifecycle()

    if (statistics.operationsCount == 0) {
        EmptyState(
            icon = Icons.Outlined.BarChart,
            title = stringResource(R.string.empty_statistics_title),
            message = stringResource(R.string.empty_statistics_message)
        )
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = stringResource(R.string.statistics_title),
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
                        Text(
                            text = stringResource(period.titleRes)
                        )
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        StatCard(
            title = stringResource(R.string.income),
            value = MoneyFormatter.format(statistics.income),
            color = IncomeColor
        )

        StatCard(
            title = stringResource(R.string.expense),
            value = MoneyFormatter.format(statistics.expense),
            color = ExpenseColor
        )

        StatCard(
            title = stringResource(R.string.balance),
            value = MoneyFormatter.format(statistics.balance),
            color = MaterialTheme.colorScheme.primary
        )

        StatCard(
            title = stringResource(R.string.operations),
            value = statistics.operationsCount.toString(),
            color = MaterialTheme.colorScheme.secondary
        )

        Text(
            text = stringResource(R.string.expenses_by_category),
            style = MaterialTheme.typography.titleLarge
        )

        val maxExpense = statistics.expenseByCategory
            .maxOfOrNull { it.amount }
            ?: 1.0

        statistics.expenseByCategory.forEach { category ->

            val progress = (category.amount / maxExpense).toFloat()

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "${Categories.findEmoji(category.category)} ${category.category}",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        Text(
                            text = MoneyFormatter.format(category.amount),
                            color = ExpenseColor,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .background(
                                MaterialTheme.colorScheme.surfaceVariant,
                                RoundedCornerShape(100)
                            )
                    ) {

                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(progress)
                                .background(
                                    ExpenseColor,
                                    RoundedCornerShape(100)
                                )
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun StatCard(
    title: String,
    value: String,
    color: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp)
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
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = color
            )
        }
    }
}