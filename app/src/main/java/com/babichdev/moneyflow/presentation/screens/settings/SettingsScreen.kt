package com.babichdev.moneyflow.presentation.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Savings
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.babichdev.moneyflow.R
import com.babichdev.moneyflow.presentation.app.AppViewModel

@Composable
fun SettingsScreen(
    viewModel: AppViewModel
) {

    val darkTheme by viewModel.darkTheme.collectAsStateWithLifecycle(
        initialValue = false
    )

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
                icon = { Icon(Icons.Outlined.Savings, null) },
                title = stringResource(R.string.currency),
                value = "₽"
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
}

@Composable
private fun SettingsItem(
    icon: @Composable () -> Unit,
    title: String,
    value: String
) {
    Card(
        modifier = Modifier.fillMaxWidth()
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