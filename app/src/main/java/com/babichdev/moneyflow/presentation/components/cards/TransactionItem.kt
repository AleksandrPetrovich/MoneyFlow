package com.babichdev.moneyflow.presentation.components.cards

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.babichdev.moneyflow.presentation.model.Categories
import com.babichdev.moneyflow.presentation.model.Currency
import com.babichdev.moneyflow.presentation.model.TransactionUi
import com.babichdev.moneyflow.ui.theme.ExpenseColor
import com.babichdev.moneyflow.ui.theme.IncomeColor
import com.babichdev.moneyflow.util.DateFormatter
import com.babichdev.moneyflow.util.MoneyFormatter

@Composable
fun TransactionItem(
    transaction: TransactionUi,
    currency: Currency,
    onLongClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .combinedClickable(
                onClick = {},
                onLongClick = onLongClick
            ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
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
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = if (transaction.isIncome)
                            "+${MoneyFormatter.format(
                                transaction.amount,
                                currency
                            )}"
                        else
                            "-${MoneyFormatter.format(
                                transaction.amount,
                                currency
                            )}",
                        color = if (transaction.isIncome)
                            IncomeColor
                        else
                            ExpenseColor,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }

                if (transaction.comment.isNotBlank()) {

                    Text(
                        text = transaction.comment,
                        style = MaterialTheme.typography.bodyMedium,
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