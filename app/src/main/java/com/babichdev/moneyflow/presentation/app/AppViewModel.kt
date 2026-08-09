package com.babichdev.moneyflow.presentation.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.babichdev.moneyflow.data.preferences.SettingsRepository
import com.babichdev.moneyflow.domain.usecase.ExportTransactionsUseCase
import com.babichdev.moneyflow.presentation.model.Currency
import com.babichdev.moneyflow.presentation.model.SettingsUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AppViewModel(
    private val settingsRepository: SettingsRepository,
    private val exportTransactionsUseCase: ExportTransactionsUseCase
) : ViewModel() {


    val settings =
        combine(
            settingsRepository.darkTheme,
            settingsRepository.currency
        ) { darkTheme, currency ->

            SettingsUiState(
                darkTheme = darkTheme,
                currency = currency
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SettingsUiState()
        )

    private val _exportCsv = MutableSharedFlow<String>()

    val exportCsv =
        _exportCsv.asSharedFlow()


    fun setDarkTheme(
        enabled: Boolean
    ) {
        viewModelScope.launch {
            settingsRepository.setDarkTheme(enabled)
        }
    }


    fun setCurrency(
        currency: Currency
    ) {
        viewModelScope.launch {
            settingsRepository.setCurrency(currency)
        }
    }


    fun exportTransactions() {
        viewModelScope.launch {

            val csv =
                exportTransactionsUseCase()

            _exportCsv.emit(csv)

        }
    }
}