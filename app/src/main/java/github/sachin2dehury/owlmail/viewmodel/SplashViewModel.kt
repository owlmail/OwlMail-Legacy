package github.sachin2dehury.owlmail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.repository.AuthRepository
import github.sachin2dehury.owlmail.repository.DataStoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _tokenState = MutableLiveData<String>()
    
    private val _credentialState = MutableLiveData<String>()

    private val _baseUrlState = MutableLiveData<String>()

    private suspend fun updateLoginTokenState() =
        dataStoreRepository.readString(R.string.key_token)?.let {
            _tokenState.postValue(it)
        }

    private suspend fun updateLoginCredentialState() =
        dataStoreRepository.readString(R.string.key_credential)?.let {
            _credentialState.postValue(it)
        }

    private suspend fun updateBaseUrlState() =
        dataStoreRepository.readString(R.string.key_url)?.let {
            _baseUrlState.postValue(it)
        }

    private fun updateInfo() = viewModelScope.launch(Dispatchers.IO) {
        updateBaseUrlState()
        updateLoginTokenState()
        updateLoginCredentialState()
    }

    init {
        updateInfo()
    }
}