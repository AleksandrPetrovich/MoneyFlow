package com.babichdev.moneyflow.presentation.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.babichdev.moneyflow.data.preferences.SettingsRepository

class AppViewModelFactory(
    private val settingsRepository: SettingsRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (modelClass.isAssignableFrom(AppViewModel::class.java)) {
            return AppViewModel(settingsRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}