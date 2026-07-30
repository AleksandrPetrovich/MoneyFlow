package com.babichdev.moneyflow.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsRepository(
    private val context: Context
) {

    val darkTheme: Flow<Boolean> =
        context.dataStore.data.map { preferences ->

            preferences[SettingsKeys.DARK_THEME] ?: false

        }

    suspend fun setDarkTheme(
        enabled: Boolean
    ) {

        context.dataStore.edit { preferences ->

            preferences[SettingsKeys.DARK_THEME] = enabled

        }

    }
}