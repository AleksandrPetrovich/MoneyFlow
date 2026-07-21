package com.babichdev.moneyflow.presentation.screens.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddScreen() {

    var isIncome by remember { mutableStateOf(false) }
    var amount by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var comment by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Новая операция",
            style = MaterialTheme.typography.headlineSmall
        )

        Column {

            RowOption(
                text = "Расход",
                selected = !isIncome
            ) {
                isIncome = false
            }

            RowOption(
                text = "Доход",
                selected = isIncome
            ) {
                isIncome = true
            }
        }

        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Сумма") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = category,
            onValueChange = { category = it },
            label = { Text("Категория") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = comment,
            onValueChange = { comment = it },
            label = { Text("Комментарий") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Сохранить")
        }
    }
}

@Composable
private fun RowOption(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    androidx.compose.foundation.layout.Row(
        modifier = Modifier
            .fillMaxWidth()
            .selectable(
                selected = selected,
                onClick = onClick
            )
            .padding(vertical = 4.dp)
    ) {

        RadioButton(
            selected = selected,
            onClick = onClick
        )

        Text(
            text = text,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}