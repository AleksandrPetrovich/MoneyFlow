package com.babichdev.moneyflow.presentation.components.cards

import androidx.compose.foundation.combinedClickable
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
import com.babichdev.moneyflow.presentation.model.Categories
import com.babichdev.moneyflow.presentation.model.TransactionUi
import com.babichdev.moneyflow.util.DateFormatter
import com.babichdev.moneyflow.util.MoneyFormatter

@Composable
fun TransactionItem(
    transaction: TransactionUi,
    onLongClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .combinedClickable(
                onClick = {},
                onLongClick = onLongClick
            )
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

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "${Categories.findEmoji(transaction.category)} ${transaction.category}",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Text(
                        text = if (transaction.isIncome)
                            "+${MoneyFormatter.format(transaction.amount)}"
                        else
                            "-${MoneyFormatter.format(transaction.amount)}",
                        color = if (transaction.isIncome)
                            Color(0xFF2E7D32)
                        else
                            Color(0xFFC62828),
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                if (transaction.comment.isNotBlank()) {

                    Text(
                        text = transaction.comment,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                Text(
                    text = DateFormatter.format(transaction.date),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}