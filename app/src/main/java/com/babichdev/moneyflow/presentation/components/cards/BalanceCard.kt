package com.babichdev.moneyflow.presentation.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.babichdev.moneyflow.R
import com.babichdev.moneyflow.util.MoneyFormatter

@Composable
fun BalanceCard(
    balance: Double,
    income: Double,
    expense: Double
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {

            Text(
                text = stringResource(R.string.total_balance),
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = MoneyFormatter.format(balance),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column {
                    Text(
                        text = stringResource(R.string.income),
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = MoneyFormatter.format(income),
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF2E7D32),
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Column {
                    Text(
                        text = stringResource(R.string.expense),
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = MoneyFormatter.format(expense),
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFFC62828),
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}