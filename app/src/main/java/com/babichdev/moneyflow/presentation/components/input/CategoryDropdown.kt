package com.babichdev.moneyflow.presentation.components.input

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.babichdev.moneyflow.presentation.model.Categories

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDropdown(
    selectedCategory: String,
    isIncome: Boolean,
    onCategorySelected: (String) -> Unit
) {

    var expanded by remember {
        mutableStateOf(false)
    }


    val categories =
        if (isIncome) {
            Categories.income
        } else {
            Categories.expense
        }


    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
        modifier = Modifier.fillMaxWidth()
    ) {


        OutlinedTextField(
            value = selectedCategory,
            onValueChange = {},
            readOnly = true,

            label = {
                Text("Категория")
            },

            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Category,
                    contentDescription = null
                )
            },

            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },

            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor()
        )


        if (expanded) {

            Popup(
                onDismissRequest = {
                    expanded = false
                }
            ) {

                Surface(
                    modifier = Modifier
                        .width(300.dp)
                        .height(400.dp),
                    shadowElevation = 20.dp,
                    color = Color.White
                ) {

                    Column {

                        Text(
                            text = "TEST MENU",
                            modifier = Modifier.padding(16.dp)
                        )


                        categories.forEach { item ->

                            TextButton(
                                onClick = {

                                    println(
                                        "CATEGORY SELECTED ${item.title}"
                                    )

                                    onCategorySelected(
                                        item.title
                                    )

                                    expanded = false
                                },

                                modifier = Modifier.fillMaxWidth()

                            ) {

                                Text(
                                    "${item.emoji} ${item.title}"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}