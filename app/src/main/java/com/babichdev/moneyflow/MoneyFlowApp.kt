package com.babichdev.moneyflow

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.babichdev.moneyflow.di.AppContainer
import com.babichdev.moneyflow.presentation.app.AppViewModel
import com.babichdev.moneyflow.presentation.components.navigation.BottomNavigationBar
import com.babichdev.moneyflow.presentation.navigation.AppNavHost

@Composable
fun MoneyFlowApp(
    appContainer: AppContainer,
    appViewModel: AppViewModel
) {

    val navController = rememberNavController()

    Scaffold(

        bottomBar = {
            BottomNavigationBar(navController)
        }

    ) { innerPadding ->

        AppNavHost(
            navController = navController,
            appContainer = appContainer,
            appViewModel = appViewModel,
            modifier = Modifier.padding(innerPadding)
        )
    }
}