package com.babichdev.moneyflow.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.babichdev.moneyflow.presentation.model.Currency
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsRepository(
    private val context: Context
) {

    val darkTheme: Flow<Boolean> =
        context.dataStore.data.map { preferences ->

            preferences[SettingsKeys.DARK_THEME] ?: false

        }


    val currency: Flow<Currency> =
        context.dataStore.data.map { preferences ->

            val value =
                preferences[SettingsKeys.CURRENCY]

            Currency.entries.find {
                it.name == value
            } ?: Currency.RUB

        }


    suspend fun setDarkTheme(
        enabled: Boolean
    ) {

        context.dataStore.edit { preferences ->

            preferences[SettingsKeys.DARK_THEME] = enabled

        }

    }


    suspend fun setCurrency(
        currency: Currency
    ) {

        context.dataStore.edit { preferences ->

            preferences[SettingsKeys.CURRENCY] =
                currency.name

        }

    }
}