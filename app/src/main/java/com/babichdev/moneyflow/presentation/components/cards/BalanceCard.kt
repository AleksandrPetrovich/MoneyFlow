package com.babichdev.moneyflow.presentation.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDownward
import androidx.compose.material.icons.outlined.ArrowUpward
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.babichdev.moneyflow.R
import com.babichdev.moneyflow.presentation.model.Currency
import com.babichdev.moneyflow.ui.theme.ExpenseColor
import com.babichdev.moneyflow.ui.theme.IncomeColor
import com.babichdev.moneyflow.util.MoneyFormatter

@Composable
fun BalanceCard(
    balance: Double,
    income: Double,
    expense: Double,
    currency: Currency
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {

            Text(
                text = stringResource(R.string.total_balance),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = MoneyFormatter.format(
                    balance,
                    currency
                ),
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowUpward,
                        contentDescription = null,
                        tint = IncomeColor
                    )

                    Text(
                        text = stringResource(R.string.income),
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = MoneyFormatter.format(
                            income,
                            currency
                        ),
                        style = MaterialTheme.typography.titleMedium,
                        color = IncomeColor,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowDownward,
                        contentDescription = null,
                        tint = ExpenseColor
                    )

                    Text(
                        text = stringResource(R.string.expense),
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = MoneyFormatter.format(
                            expense,
                            currency
                        ),
                        style = MaterialTheme.typography.titleMedium,
                        color = ExpenseColor,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}