package com.babichdev.moneyflow.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {

    data object Home : Screen(
        route = "home",
        title = "Главная",
        icon = Icons.Outlined.Home
    )

    data object History : Screen(
        route = "history",
        title = "История",
        icon = Icons.Outlined.List
    )

    object Add : Screen(
        route = "add?transactionId={transactionId}",
        title = "Добавить",
        icon = Icons.Default.Add
    )

    fun createRoute(transactionId: Long? = null): String {
        return if (transactionId == null) {
            "add"
        } else {
            "add?transactionId=$transactionId"
        }
    }

    data object Statistics : Screen(
        route = "statistics",
        title = "Статистика",
        icon = Icons.Outlined.BarChart
    )

    data object Settings : Screen(
        route = "settings",
        title = "Настройки",
        icon = Icons.Outlined.Settings
    )

    companion object {
        val bottomNavigationItems = listOf(
            Home,
            History,
            Add,
            Statistics,
            Settings
        )
    }
}