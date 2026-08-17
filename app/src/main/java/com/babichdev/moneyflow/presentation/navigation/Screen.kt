package com.babichdev.moneyflow.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.babichdev.moneyflow.R

sealed class Screen(
    val route: String,
    val titleRes: Int,
    val icon: ImageVector
) {

    data object Home : Screen(
        route = "home",
        titleRes = R.string.home,
        icon = Icons.Outlined.Home
    )

    data object History : Screen(
        route = "history",
        titleRes = R.string.history,
        icon = Icons.Outlined.List
    )

    object Add : Screen(
        route = "add?transactionId={transactionId}",
        titleRes = R.string.add,
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
        titleRes = R.string.statistics,
        icon = Icons.Outlined.BarChart
    )

    data object Settings : Screen(
        route = "settings",
        titleRes = R.string.settings,
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