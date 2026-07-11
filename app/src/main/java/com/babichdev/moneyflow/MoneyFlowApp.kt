package com.babichdev.moneyflow

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.babichdev.moneyflow.presentation.components.navigation.BottomNavigationBar
import com.babichdev.moneyflow.presentation.navigation.AppNavHost

@Composable
fun MoneyFlowApp() {

    val navController = rememberNavController()

    Scaffold(

        bottomBar = {
            BottomNavigationBar(navController)
        }

    ) { innerPadding ->

        AppNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}