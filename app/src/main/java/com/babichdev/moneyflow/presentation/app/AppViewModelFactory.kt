package com.babichdev.moneyflow.presentation.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.babichdev.moneyflow.data.preferences.SettingsRepository
import com.babichdev.moneyflow.domain.usecase.ExportTransactionsUseCase

class AppViewModelFactory(
    private val settingsRepository: SettingsRepository,
    private val exportTransactionsUseCase: ExportTransactionsUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (modelClass.isAssignableFrom(AppViewModel::class.java)) {
            return AppViewModel(
                settingsRepository = settingsRepository,
                exportTransactionsUseCase = exportTransactionsUseCase
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}