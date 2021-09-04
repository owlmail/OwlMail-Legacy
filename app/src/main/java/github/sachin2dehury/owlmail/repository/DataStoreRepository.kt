package github.sachin2dehury.owlmail.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import github.sachin2dehury.owlmail.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DataStoreRepository(
    private val context: Context,
    private val dataStore: DataStore<Preferences>
) {

    fun saveString(key: Int, value: String) = CoroutineScope(Dispatchers.IO).launch {
        dataStore.edit { settings ->
            settings[stringPreferencesKey(context.getString(key))] = value
        }
    }

    fun saveBoolean(key: Int, value: Boolean) = CoroutineScope(Dispatchers.IO).launch {
        dataStore.edit { settings ->
            settings[booleanPreferencesKey(context.getString(key))] = value
        }
    }

    fun readString(key: Int) = dataStore.data.map { preferences ->
        preferences[stringPreferencesKey(context.getString(key))]
    }

    fun readBoolean(key: Int) = dataStore.data.map { preferences ->
        preferences[booleanPreferencesKey(context.getString(key))]
    }

    fun resetLogin() {
        saveString(R.string.key_credential, "")
        saveString(R.string.key_token, "")
        saveBoolean(R.string.key_should_sync, false)
    }

    fun resetBaseURL() = saveString(R.string.key_url, "")
}