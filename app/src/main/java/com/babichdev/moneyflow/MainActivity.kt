package com.babichdev.moneyflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.babichdev.moneyflow.presentation.app.AppViewModel
import com.babichdev.moneyflow.presentation.app.AppViewModelFactory
import com.babichdev.moneyflow.ui.theme.MoneyFlowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val container =
                (application as MoneyFlowApplication).container

            val appViewModel: AppViewModel = viewModel(
                factory = AppViewModelFactory(
                    settingsRepository = container.settingsRepository,
                    exportTransactionsUseCase = container.exportTransactionsUseCase
                )
            )

            val settings by appViewModel.settings.collectAsStateWithLifecycle()

            val darkTheme = settings.darkTheme

            MoneyFlowTheme(
                darkTheme = darkTheme
            ) {

                MoneyFlowApp(
                    appContainer = container,
                    appViewModel = appViewModel
                )
            }
        }
    }
}