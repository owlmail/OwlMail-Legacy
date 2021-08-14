package github.sachin2dehury.owlmail.viewmodel

import android.content.Context
import androidx.core.os.persistableBundleOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.api.ResultState
import github.sachin2dehury.owlmail.repository.DataStoreRepository
import github.sachin2dehury.owlmail.repository.MailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val mailRepository: MailRepository,
) : ViewModel() {

    private val _darkThemeState by lazy { MutableStateFlow<ResultState>(ResultState.Loading) }
    val darkThemeState: StateFlow<ResultState> by lazy { _darkThemeState }

    private val _syncState by lazy { MutableStateFlow<ResultState>(ResultState.Loading) }
    val syncState: StateFlow<ResultState> by lazy { _syncState }

    private val _analyticsState by lazy { MutableStateFlow<ResultState>(ResultState.Loading) }
    val analyticsState: StateFlow<ResultState> by lazy { _analyticsState }

    private fun updateDarkThemeState() = viewModelScope.launch(Dispatchers.IO) {
        dataStoreRepository.readBoolean(R.string.key_dark_theme)
            .catch { throwable ->
                _darkThemeState.value = ResultState.Error(throwable.message)
            }.collectLatest {
                if (it != null) {
                    _darkThemeState.value = ResultState.Success(it)
                } else {
                    _darkThemeState.value = ResultState.Error()
                }
            }
    }

    private fun updateSyncState() = viewModelScope.launch(Dispatchers.IO) {
        dataStoreRepository.readBoolean(R.string.key_should_sync).catch { throwable ->
            _syncState.value = ResultState.Error(throwable.message)
        }.collectLatest {
            if (it != null) {
                _syncState.value = ResultState.Success(it)
            } else {
                _syncState.value = ResultState.Error()
            }
        }
    }

    private fun updateAnalyticsState() = viewModelScope.launch(Dispatchers.IO) {
        dataStoreRepository.readBoolean(R.string.key_analytics).catch { throwable ->
            _analyticsState.value = ResultState.Error(throwable.message)
        }.collectLatest {
            if (it != null) {
                _analyticsState.value = ResultState.Success(it)
            } else {
                _analyticsState.value = ResultState.Error()
            }
        }
    }

    init {
        updateDarkThemeState()
        updateSyncState()
        updateAnalyticsState()
    }

    fun saveDarkThemeState(isEnabled: Boolean) =
        dataStoreRepository.saveBoolean(R.string.key_dark_theme, isEnabled)

    fun saveSyncState(isEnabled: Boolean) =
        dataStoreRepository.saveBoolean(R.string.key_should_sync, isEnabled)

    fun saveAnalyticsState(isEnabled: Boolean) =
        dataStoreRepository.saveBoolean(R.string.key_analytics, isEnabled)

    //TODO improve logic here
    fun getBundle(context: Context) = persistableBundleOf(
        context.getString(R.string.key_should_sync) to syncState.value,
        context.getString(R.string.key_token) to mailRepository.getToken(),
        context.getString(R.string.key_credential) to mailRepository.getCredential()
    )
}