package com.babichdev.moneyflow.presentation.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Savings
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.babichdev.moneyflow.R
import com.babichdev.moneyflow.presentation.app.AppViewModel
import com.babichdev.moneyflow.presentation.model.Currency
import com.babichdev.moneyflow.presentation.model.displayNameRes

@Composable
fun SettingsScreen(
    viewModel: AppViewModel,
    onExportClick: () -> Unit
) {

    val settings by viewModel.settings.collectAsStateWithLifecycle()

    val darkTheme = settings.darkTheme

    val currency = settings.currency

    var showCurrencyDialog by remember {
        mutableStateOf(false)
    }

    println("SettingsScreen recomposed, dialog = $showCurrencyDialog")

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            item {
                Text(
                    text = stringResource(R.string.settings),
                    style = MaterialTheme.typography.headlineSmall
                )
            }

            item {
                SettingsItem(
                    icon = { Icon(Icons.Outlined.Person, null) },
                    title = stringResource(R.string.profile),
                    value = stringResource(R.string.user)
                )
            }

            item {

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector = Icons.Outlined.DarkMode,
                            contentDescription = null
                        )

                        Text(
                            text = stringResource(R.string.dark_theme),
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 16.dp),
                            style = MaterialTheme.typography.titleMedium
                        )

                        Switch(
                            checked = darkTheme,
                            onCheckedChange = {
                                viewModel.setDarkTheme(it)
                            }
                        )
                    }
                }
            }

            item {

                SettingsItem(
                    icon = {
                        Icon(Icons.Outlined.Savings, null)
                    },
                    title = stringResource(R.string.currency),
                    value = currency.symbol,
                    onClick = {
                        println("Currency clicked")
                        showCurrencyDialog = true
                    }
                )

            }

            item {
                SettingsItem(
                    icon = {
                        Icon(Icons.Outlined.FileDownload, null)
                    },
                    title = stringResource(R.string.export_transactions),
                    value = "",
                    onClick = {
                        viewModel.exportTransactions()
                    }
                )
            }

            item {
                SettingsItem(
                    icon = { Icon(Icons.Outlined.Notifications, null) },
                    title = stringResource(R.string.notifications),
                    value = stringResource(R.string.enabled)
                )
            }

            item {
                SettingsItem(
                    icon = { Icon(Icons.Outlined.Info, null) },
                    title = stringResource(R.string.about_app),
                    value = stringResource(R.string.app_version)
                )
            }
        }

        if (showCurrencyDialog) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Color.Black.copy(alpha = 0.5f)
                    )
            ) {

                Card(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(32.dp),
                    shape = RoundedCornerShape(24.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(24.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {

                        Text(
                            text = stringResource(R.string.currency),
                            style = MaterialTheme.typography.titleLarge
                        )

                        Currency.entries.forEach { item ->

                            TextButton(
                                onClick = {
                                    viewModel.setCurrency(item)
                                    showCurrencyDialog = false
                                }
                            ) {

                                Text(
                                    text =
                                        "${stringResource(item.displayNameRes)} ${item.symbol}"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

    @Composable
    private fun SettingsItem(
        icon: @Composable () -> Unit,
        title: String,
        value: String,
        onClick: () -> Unit = {}
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp),
            onClick = onClick
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                icon()

                Text(
                    text = title,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp),
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = value,
                    style = MaterialTheme.typography.bodyMedium
                )

                Icon(
                    imageVector = Icons.Outlined.ChevronRight,
                    contentDescription = null,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }