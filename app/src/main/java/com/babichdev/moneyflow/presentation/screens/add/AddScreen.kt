package com.babichdev.moneyflow.presentation.screens.add

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Card
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.babichdev.moneyflow.R
import com.babichdev.moneyflow.presentation.model.Categories
import kotlinx.coroutines.flow.collectLatest
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun AddScreen(
    viewModel: AddViewModel,
    onSaved: () -> Unit
) {

    LaunchedEffect(Unit) {
        viewModel.transactionSaved.collectLatest {
            onSaved()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.transactionDeleted.collectLatest {
            onSaved()
        }
    }

    val amount by viewModel.amount.collectAsState()
    val category by viewModel.category.collectAsState()
    val comment by viewModel.comment.collectAsState()
    val isIncome by viewModel.isIncome.collectAsState()
    val date by viewModel.date.collectAsState()
    val canSave by viewModel.canSave.collectAsState()

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

    var showCategoryDialog by remember {
        mutableStateOf(false)
    }

    val categories =
        if (isIncome) {
            Categories.income
        } else {
            Categories.expense
        }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = stringResource(
                if (viewModel.isEditMode) {
                    R.string.edit_transaction
                } else {
                    R.string.new_transaction
                }
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


        // КАТЕГОРИЯ

        OutlinedTextField(
            value = category,
            onValueChange = {},
            readOnly = true,
            enabled = false,
            label = {
                Text(stringResource(R.string.category))
            },
            trailingIcon = {
                Text("▼")
            },
            modifier = Modifier
                .fillMaxWidth()
                .clickable {

                    println("CATEGORY FIELD CLICKED")

                    showCategoryDialog = true
                }
        )


        // ДАТА

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
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    showDatePicker = true
                }
        )


        // КОММЕНТАРИЙ

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


        // СОХРАНИТЬ

        Button(
            enabled = canSave,
            onClick = {
                viewModel.saveTransaction()
            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                stringResource(
                    if (viewModel.isEditMode) {
                        R.string.update
                    } else {
                        R.string.save
                    }
                )
            )
        }


        // УДАЛИТЬ

        if (viewModel.isEditMode) {

            Button(
                onClick = {
                    showDeleteDialog = true
                },
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    stringResource(R.string.delete)
                )
            }
        }
    }


    if (showCategoryDialog) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color.Black.copy(alpha = 0.5f)
                ),
            contentAlignment = Alignment.Center
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {

                Column(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    Text(
                        text = stringResource(R.string.category),
                        style = MaterialTheme.typography.headlineSmall
                    )


                    categories.forEach { item ->

                        TextButton(
                            onClick = {

                                println(
                                    "CATEGORY SELECTED: ${item.title}"
                                )


                                viewModel.onCategoryChanged(
                                    item.title
                                )


                                showCategoryDialog = false
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            Text(
                                "${item.emoji} ${item.title}"
                            )
                        }
                    }


                    TextButton(
                        onClick = {
                            showCategoryDialog = false
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Text(
                            stringResource(R.string.cancel)
                        )
                    }
                }
            }
        }
    }


    // ДИАЛОГ УДАЛЕНИЯ

    if (showDeleteDialog) {

        AlertDialog(
            onDismissRequest = {
                showDeleteDialog = false
            },

            title = {
                Text(
                    stringResource(
                        R.string.delete_transaction_title
                    )
                )
            },

            text = {
                Text(
                    stringResource(
                        R.string.delete_transaction_message
                    )
                )
            },

            confirmButton = {

                Button(
                    onClick = {

                        showDeleteDialog = false

                        viewModel.deleteTransaction()
                    }
                ) {

                    Text(
                        stringResource(R.string.delete)
                    )
                }
            },

            dismissButton = {

                Button(
                    onClick = {
                        showDeleteDialog = false
                    }
                ) {

                    Text(
                        stringResource(R.string.cancel)
                    )
                }
            }
        )
    }


    // DATE PICKER

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

                    Text(
                        stringResource(R.string.cancel)
                    )
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

    Row(
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