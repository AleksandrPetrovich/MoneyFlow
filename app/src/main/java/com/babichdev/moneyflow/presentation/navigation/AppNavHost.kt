package com.babichdev.moneyflow.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.babichdev.moneyflow.di.AppContainer
import com.babichdev.moneyflow.presentation.app.AppViewModel
import com.babichdev.moneyflow.presentation.screens.add.AddScreen
import com.babichdev.moneyflow.presentation.screens.add.AddViewModel
import com.babichdev.moneyflow.presentation.screens.add.AddViewModelFactory
import com.babichdev.moneyflow.presentation.screens.currency.CurrencyScreen
import com.babichdev.moneyflow.presentation.screens.history.HistoryScreen
import com.babichdev.moneyflow.presentation.screens.history.HistoryViewModel
import com.babichdev.moneyflow.presentation.screens.history.HistoryViewModelFactory
import com.babichdev.moneyflow.presentation.screens.home.HomeScreen
import com.babichdev.moneyflow.presentation.screens.home.HomeViewModel
import com.babichdev.moneyflow.presentation.screens.home.HomeViewModelFactory
import com.babichdev.moneyflow.presentation.screens.settings.SettingsScreen
import com.babichdev.moneyflow.presentation.screens.statistics.StatisticsScreen
import com.babichdev.moneyflow.presentation.screens.statistics.StatisticsViewModel
import com.babichdev.moneyflow.presentation.screens.statistics.StatisticsViewModelFactory

@Composable
fun AppNavHost(
    navController: NavHostController,
    appContainer: AppContainer,
    appViewModel: AppViewModel,
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

            HomeScreen(
                viewModel = viewModel,
                currency = appViewModel.currency,
                onEditTransaction = { transactionId ->
                    navController.navigate(
                        Screen.Add.createRoute(transactionId)
                    )
                }
            )
        }

        composable(Screen.History.route) {

            val viewModel: HistoryViewModel = viewModel(
                factory = HistoryViewModelFactory(
                    appContainer.repository
                )
            )

            HistoryScreen(
                viewModel = viewModel,
                currency = appViewModel.currency
            )
        }

        composable(
            route = Screen.Add.route,
            arguments = listOf(
                navArgument("transactionId") {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )
        ) { backStackEntry ->

            val transactionId =
                backStackEntry.arguments?.getLong("transactionId") ?: -1L

            val viewModel: AddViewModel = viewModel(
                factory = AddViewModelFactory(
                    repository = appContainer.repository,
                    transactionId = transactionId
                )
            )

            AddScreen(
                viewModel = viewModel,
                onSaved = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.Statistics.route) {

            val viewModel: StatisticsViewModel = viewModel(
                factory = StatisticsViewModelFactory(
                    appContainer.repository
                )
            )

            StatisticsScreen(
                viewModel = viewModel,
                currency = appViewModel.currency
            )
        }

        composable(Screen.Settings.route) {

            SettingsScreen(
                viewModel = appViewModel,
                onExportClick = {}
            )
        }

        composable(Screen.Currency.route) {

            CurrencyScreen()

        }
    }
}