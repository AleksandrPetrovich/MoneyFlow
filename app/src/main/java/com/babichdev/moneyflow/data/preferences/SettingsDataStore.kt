package com.babichdev.moneyflow.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

private const val SETTINGS_NAME = "settings"

val Context.dataStore by preferencesDataStore(
    name = SETTINGS_NAME
)

object SettingsKeys {

    val DARK_THEME =
        booleanPreferencesKey("dark_theme")

    val CURRENCY =
        stringPreferencesKey("currency")
}