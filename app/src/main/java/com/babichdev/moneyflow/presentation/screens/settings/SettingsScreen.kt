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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen() {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        item {
            Text(
                text = "Настройки",
                style = MaterialTheme.typography.headlineSmall
            )
        }

        item {
            SettingsItem(
                icon = { Icon(Icons.Outlined.Person, null) },
                title = "Профиль",
                value = "Пользователь"
            )
        }

        item {
            SettingsItem(
                icon = { Icon(Icons.Outlined.DarkMode, null) },
                title = "Тёмная тема",
                value = "Системная"
            )
        }

        item {
            SettingsItem(
                icon = { Icon(Icons.Outlined.Savings, null) },
                title = "Валюта",
                value = "₽"
            )
        }

        item {
            SettingsItem(
                icon = { Icon(Icons.Outlined.Notifications, null) },
                title = "Уведомления",
                value = "Включены"
            )
        }

        item {
            SettingsItem(
                icon = { Icon(Icons.Outlined.Info, null) },
                title = "О приложении",
                value = "v1.0.0"
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