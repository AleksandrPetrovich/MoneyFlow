package com.babichdev.moneyflow.presentation.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.babichdev.moneyflow.data.preferences.SettingsRepository
import kotlinx.coroutines.launch

class AppViewModel(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    val darkTheme = settingsRepository.darkTheme

    fun setDarkTheme(
        enabled: Boolean
    ) {
        viewModelScope.launch {
            settingsRepository.setDarkTheme(enabled)
        }
    }
}