package com.babichdev.moneyflow.presentation.screens.statistics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun StatisticsScreen() {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            Text(
                text = "Статистика",
                style = MaterialTheme.typography.headlineSmall
            )
        }

        item {
            StatisticCard(
                title = "Доходы",
                value = "154 000 ₽"
            )
        }

        item {
            StatisticCard(
                title = "Расходы",
                value = "28 570 ₽"
            )
        }

        item {
            Text(
                text = "Распределение расходов",
                style = MaterialTheme.typography.titleLarge
            )
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(180.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primaryContainer)
                )
            }
        }

        item {
            Text(
                text = "Категории",
                style = MaterialTheme.typography.titleLarge
            )
        }

        item {
            StatisticCard("🍔 Еда", "12 500 ₽")
        }

        item {
            StatisticCard("🚗 Транспорт", "3 200 ₽")
        }

        item {
            StatisticCard("🎮 Развлечения", "5 400 ₽")
        }

        item {
            StatisticCard("🏠 Дом", "7 470 ₽")
        }
    }
}

@Composable
private fun StatisticCard(
    title: String,
    value: String
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        androidx.compose.foundation.layout.Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}