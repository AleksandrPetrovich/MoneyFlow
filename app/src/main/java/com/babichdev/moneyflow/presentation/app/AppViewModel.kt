package com.babichdev.moneyflow.presentation.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.babichdev.moneyflow.data.preferences.SettingsRepository
import com.babichdev.moneyflow.domain.usecase.ExportTransactionsUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AppViewModel(
    private val settingsRepository: SettingsRepository,
    private val exportTransactionsUseCase: ExportTransactionsUseCase
) : ViewModel() {

    val darkTheme = settingsRepository.darkTheme

    private val _exportCsv = MutableSharedFlow<String>()
    val exportCsv = _exportCsv.asSharedFlow()

    fun setDarkTheme(
        enabled: Boolean
    ) {
        viewModelScope.launch {
            settingsRepository.setDarkTheme(enabled)
        }
    }

    fun exportTransactions() {
        viewModelScope.launch {
            val csv = exportTransactionsUseCase()
            _exportCsv.emit(csv)
        }
    }
}