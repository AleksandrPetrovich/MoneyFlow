package com.babichdev.moneyflow.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.babichdev.moneyflow.di.AppContainer
import com.babichdev.moneyflow.presentation.screens.add.AddScreen
import com.babichdev.moneyflow.presentation.screens.add.AddViewModel
import com.babichdev.moneyflow.presentation.screens.add.AddViewModelFactory
import com.babichdev.moneyflow.presentation.screens.history.HistoryScreen
import com.babichdev.moneyflow.presentation.screens.history.HistoryViewModel
import com.babichdev.moneyflow.presentation.screens.history.HistoryViewModelFactory
import com.babichdev.moneyflow.presentation.screens.home.HomeScreen
import com.babichdev.moneyflow.presentation.screens.home.HomeViewModel
import com.babichdev.moneyflow.presentation.screens.home.HomeViewModelFactory
import com.babichdev.moneyflow.presentation.screens.settings.SettingsScreen
import com.babichdev.moneyflow.presentation.screens.statistics.StatisticsScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    appContainer: AppContainer,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {

        composable(Screen.Home.route) {

            val viewModel: HomeViewModel = viewModel(
                factory = HomeViewModelFactory(
                    appContainer.repository
                )
            )

            HomeScreen(viewModel)
        }

        composable(Screen.History.route) {

            val viewModel: HistoryViewModel = viewModel(
                factory = HistoryViewModelFactory(
                    appContainer.repository
                )
            )

            HistoryScreen(
                viewModel = viewModel
            )
        }

        composable(Screen.Add.route) {

            val viewModel: AddViewModel = viewModel(
                factory = AddViewModelFactory(
                    appContainer.repository
                )
            )

            AddScreen(
                viewModel = viewModel
            )
        }

        composable(Screen.Statistics.route) {
            StatisticsScreen()
        }

        composable(Screen.Settings.route) {
            SettingsScreen()
        }
    }
}