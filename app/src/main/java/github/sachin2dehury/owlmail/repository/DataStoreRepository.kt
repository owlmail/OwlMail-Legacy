package github.sachin2dehury.owlmail.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import github.sachin2dehury.owlmail.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext

class DataStoreRepository(
    private val context: Context,
    private val dataStore: DataStore<Preferences>,
) {

    suspend fun <T> saveData(key: Int, value: T?) = withContext(Dispatchers.IO) {
        dataStore.edit { settings ->
            when (value) {
                is String -> settings[stringPreferencesKey(context.getString(key))] = value
                is Boolean -> settings[booleanPreferencesKey(context.getString(key))] = value
                is Long -> settings[longPreferencesKey(context.getString(key))] = value
            }
        }
    }

    suspend fun <T> deleteData(key: Int, defaultValue: T) = withContext(Dispatchers.IO) {
        dataStore.edit { settings ->
            when (defaultValue) {
                is String -> settings.remove(stringPreferencesKey(context.getString(key)))
                is Boolean -> settings.remove(booleanPreferencesKey(context.getString(key)))
                is Long -> settings.remove(longPreferencesKey(context.getString(key)))
            }
        }
    }

    suspend fun readString(key: Int) = withContext(Dispatchers.IO) {
        dataStore.data.firstOrNull()?.get(stringPreferencesKey(context.getString(key)))
    }

    suspend fun readBoolean(key: Int) = withContext(Dispatchers.IO) {
        dataStore.data.firstOrNull()?.get(booleanPreferencesKey(context.getString(key)))
    }

    suspend fun readLong(key: Int) = withContext(Dispatchers.IO) {
        dataStore.data.firstOrNull()?.get(longPreferencesKey(context.getString(key)))
    }

    suspend fun resetLogin() {
        deleteData(R.string.key_url, "")
        deleteData(R.string.key_username, "")
        deleteData(R.string.key_password, "")
        deleteData(R.string.key_should_sync, false)
        deleteData(R.string.key_auth_token, "")
        deleteData(R.string.key_auth_token_expire_time, 0L)
    }
}
