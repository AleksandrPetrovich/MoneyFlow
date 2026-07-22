package com.babichdev.moneyflow.presentation.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.babichdev.moneyflow.presentation.model.TransactionUi

@Composable
fun TransactionItem(
    transaction: TransactionUi
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = transaction.category,
                    style = MaterialTheme.typography.bodyLarge
                )

                if (transaction.comment.isNotBlank()) {
                    Text(
                        text = transaction.comment,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Text(
                text = if (transaction.isIncome)
                    "+${transaction.amount.toInt()} ₽"
                else
                    "-${transaction.amount.toInt()} ₽",
                color = if (transaction.isIncome)
                    Color(0xFF2E7D32)
                else
                    Color(0xFFC62828),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}