package github.sachin2dehury.owlmail.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import github.sachin2dehury.owlmail.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class DataStoreRepository(
    private val context: Context,
    private val dataStore: DataStore<Preferences>
) {

    fun saveData(key: Int, value: Any?) = CoroutineScope(Dispatchers.IO).launch {
        dataStore.edit { settings ->
            when (value) {
                is String -> settings[stringPreferencesKey(context.getString(key))] = value
                is Boolean -> settings[booleanPreferencesKey(context.getString(key))] = value
                is Long -> settings[longPreferencesKey(context.getString(key))] = value
            }
        }
    }

    fun deleteData(key: Int, value: Any?) = CoroutineScope(Dispatchers.IO).launch {
        dataStore.edit { settings ->
            when (value) {
                is String -> settings.remove(stringPreferencesKey(context.getString(key)))
                is Boolean -> settings.remove(booleanPreferencesKey(context.getString(key)))
                is Long -> settings.remove(longPreferencesKey(context.getString(key)))
            }
        }
    }

    suspend fun readString(key: Int) =
        dataStore.data.firstOrNull()?.get(stringPreferencesKey(context.getString(key)))

    suspend fun readBoolean(key: Int) =
        dataStore.data.firstOrNull()?.get(booleanPreferencesKey(context.getString(key)))

    suspend fun readLong(key: Int) =
        dataStore.data.firstOrNull()?.get(longPreferencesKey(context.getString(key)))

    fun resetLogin() = CoroutineScope(Dispatchers.IO).launch {
        deleteData(R.string.key_url, "")
        deleteData(R.string.key_username, "")
        deleteData(R.string.key_password, "")
        deleteData(R.string.key_should_sync, false)
        deleteData(R.string.key_auth_token, "")
        deleteData(R.string.key_auth_token_expire_time, 0L)
    }
}
