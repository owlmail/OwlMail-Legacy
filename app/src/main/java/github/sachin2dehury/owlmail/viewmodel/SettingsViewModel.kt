package github.sachin2dehury.owlmail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.repository.AuthRepository
import github.sachin2dehury.owlmail.repository.DataStoreRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val dataStoreRepository: DataStoreRepository,
) : ViewModel() {

    private val _darkThemeState = MutableLiveData(false)
    val darkThemeState: LiveData<Boolean> = _darkThemeState

    private val _syncState = MutableLiveData(false)
    val syncState: LiveData<Boolean> = _syncState

    private val _analyticsState = MutableLiveData(false)
    val analyticsState: LiveData<Boolean> = _analyticsState

    private fun updateDarkThemeState() = viewModelScope.launch {
        _darkThemeState.postValue(dataStoreRepository.readBoolean(R.string.key_dark_theme) ?: false)
    }

    private fun updateSyncState() = viewModelScope.launch {
        _syncState.postValue(dataStoreRepository.readBoolean(R.string.key_should_sync) ?: false)
    }

    private fun updateAnalyticsState() = viewModelScope.launch {
        _analyticsState.postValue(dataStoreRepository.readBoolean(R.string.key_analytics) ?: false)
    }

    init {
        updateDarkThemeState()
        updateSyncState()
        updateAnalyticsState()
    }

    fun saveDarkThemeState(isEnabled: Boolean) = viewModelScope.launch {
        dataStoreRepository.saveData(R.string.key_dark_theme, isEnabled)
    }

    fun saveSyncState(isEnabled: Boolean) = viewModelScope.launch {
        dataStoreRepository.saveData(R.string.key_should_sync, isEnabled)
    }

    fun saveAnalyticsState(isEnabled: Boolean) = viewModelScope.launch {
        dataStoreRepository.saveData(R.string.key_analytics, isEnabled)
    }
}
