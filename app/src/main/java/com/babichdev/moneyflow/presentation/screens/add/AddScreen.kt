package com.babichdev.moneyflow.presentation.screens.add

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.EditNote
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.babichdev.moneyflow.R
import com.babichdev.moneyflow.presentation.components.input.CategoryDropdown
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AddScreen(
    viewModel: AddViewModel,
    onSaved: () -> Unit
) {

    val amount by viewModel.amount.collectAsStateWithLifecycle()
    val category by viewModel.category.collectAsStateWithLifecycle()
    val comment by viewModel.comment.collectAsStateWithLifecycle()
    val isIncome by viewModel.isIncome.collectAsStateWithLifecycle()
    val date by viewModel.date.collectAsStateWithLifecycle()
    val canSave by viewModel.canSave.collectAsStateWithLifecycle()

    val formattedDate = remember(date) {
        SimpleDateFormat(
            "dd.MM.yyyy",
            Locale.getDefault()
        ).format(Date(date))
    }

    var showDeleteDialog by remember {
        mutableStateOf(false)
    }

    var showDatePicker by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = stringResource(
                if (viewModel.isEditMode)
                    R.string.edit_transaction
                else
                    R.string.new_transaction
            ),
            style = MaterialTheme.typography.headlineSmall
        )

        Column {

            RowOption(
                text = stringResource(R.string.expense),
                selected = !isIncome
            ) {
                viewModel.onIncomeChanged(false)
            }

            RowOption(
                text = stringResource(R.string.income),
                selected = isIncome
            ) {
                viewModel.onIncomeChanged(true)
            }
        }

        OutlinedTextField(
            value = amount,
            onValueChange = viewModel::onAmountChanged,
            label = {
                Text(stringResource(R.string.amount))
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.AttachMoney,
                    contentDescription = null
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        CategoryDropdown(
            selectedCategory = category,
            isIncome = isIncome,
            onCategorySelected = viewModel::onCategoryChanged
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    showDatePicker = true
                }
        ) {

            OutlinedTextField(
                value = formattedDate,
                onValueChange = {},
                readOnly = true,
                enabled = false,
                label = {
                    Text(stringResource(R.string.date))
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.DateRange,
                        contentDescription = null
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )

        }

        OutlinedTextField(
            value = comment,
            onValueChange = viewModel::onCommentChanged,
            label = {
                Text(stringResource(R.string.comment))
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.EditNote,
                    contentDescription = null
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            enabled = canSave,
            onClick = {
                viewModel.saveTransaction()
                onSaved()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                stringResource(
                    if (viewModel.isEditMode)
                        R.string.update
                    else
                        R.string.save
                )
            )
        }

        if (viewModel.isEditMode) {

            Button(
                onClick = {
                    showDeleteDialog = true
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.delete))
            }

        }
    }

    if (showDeleteDialog) {

        AlertDialog(
            onDismissRequest = {
                showDeleteDialog = false
            },
            title = {
                Text(stringResource(R.string.delete_transaction_title))
            },
            text = {
                Text(stringResource(R.string.delete_transaction_message))
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDeleteDialog = false
                        viewModel.deleteTransaction()
                        onSaved()
                    }
                ) {
                    Text(stringResource(R.string.delete))
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showDeleteDialog = false
                    }
                ) {
                    Text(stringResource(R.string.cancel))
                }
            }
        )
    }

    if (showDatePicker) {

        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = date
        )

        DatePickerDialog(
            onDismissRequest = {
                showDatePicker = false
            },
            confirmButton = {

                TextButton(
                    onClick = {

                        datePickerState.selectedDateMillis?.let {
                            viewModel.onDateChanged(it)
                        }

                        showDatePicker = false
                    }
                ) {
                    Text("OK")
                }

            },
            dismissButton = {

                TextButton(
                    onClick = {
                        showDatePicker = false
                    }
                ) {
                    Text(stringResource(R.string.cancel))
                }

            }
        ) {

            DatePicker(
                state = datePickerState
            )

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