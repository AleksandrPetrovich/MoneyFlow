package com.babichdev.moneyflow.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.babichdev.moneyflow.presentation.screens.add.AddScreen
import com.babichdev.moneyflow.presentation.screens.history.HistoryScreen
import com.babichdev.moneyflow.presentation.screens.home.HomeScreen
import com.babichdev.moneyflow.presentation.screens.settings.SettingsScreen
import com.babichdev.moneyflow.presentation.screens.statistics.StatisticsScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {

        composable(Screen.Home.route) {
            HomeScreen()
        }

        composable(Screen.History.route) {
            HistoryScreen()
        }

        composable(Screen.Add.route) {
            AddScreen()
        }

        composable(Screen.Statistics.route) {
            StatisticsScreen()
        }

        composable(Screen.Settings.route) {
            SettingsScreen()
        }
    }
}