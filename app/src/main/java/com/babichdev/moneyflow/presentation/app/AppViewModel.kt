package com.babichdev.moneyflow.presentation.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.babichdev.moneyflow.data.preferences.SettingsRepository
import com.babichdev.moneyflow.domain.usecase.ExportTransactionsUseCase
import com.babichdev.moneyflow.presentation.model.Currency
import com.babichdev.moneyflow.presentation.model.SettingsUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppViewModel(
    private val settingsRepository: SettingsRepository,
    private val exportTransactionsUseCase: ExportTransactionsUseCase
) : ViewModel() {


    private val _settings =
        MutableStateFlow(
            SettingsUiState()
        )

    val settings =
        _settings.asStateFlow()

    private val _exportCsv = MutableSharedFlow<String>()

    val exportCsv =
        _exportCsv.asSharedFlow()


    fun setDarkTheme(
        enabled: Boolean
    ) {
        viewModelScope.launch {

            settingsRepository.setDarkTheme(
                enabled
            )

            _settings.update {
                it.copy(
                    darkTheme = enabled
                )
            }
        }
    }


    fun setCurrency(
        currency: Currency
    ) {
        viewModelScope.launch {

            settingsRepository.setCurrency(
                currency
            )

            _settings.update {
                it.copy(
                    currency = currency
                )
            }
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