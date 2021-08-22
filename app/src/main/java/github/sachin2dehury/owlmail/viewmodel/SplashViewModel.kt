package github.sachin2dehury.owlmail.viewmodel

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
class SplashViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val mailRepository: MailRepository,
) : ViewModel() {

    private val _tokenState by lazy { MutableStateFlow<ResultState<String>>(ResultState.Loading) }
    val tokenState: StateFlow<ResultState<String>> by lazy { _tokenState }

    private val _credentialState by lazy { MutableStateFlow<ResultState<String>>(ResultState.Loading) }
    val credentialState: StateFlow<ResultState<String>> by lazy { _credentialState }

    private val _baseUrlState by lazy { MutableStateFlow<ResultState<String>>(ResultState.Loading) }
    val baseUrlState: StateFlow<ResultState<String>> by lazy { _baseUrlState }

    private fun updateLoginTokenState() = viewModelScope.launch(Dispatchers.IO) {
        dataStoreRepository.readString(R.string.key_token).catch { throwable ->
            _tokenState.value = ResultState.Error(throwable.message)
        }.collectLatest {
            if (it != null) {
                _tokenState.value = ResultState.Success(it)
                mailRepository.setToken(it)
            } else {
                _tokenState.value = ResultState.Error()
            }
        }
    }

    private fun updateLoginCredentialState() = viewModelScope.launch(Dispatchers.IO) {
        dataStoreRepository.readString(R.string.key_credential).catch { throwable ->
            _credentialState.value = ResultState.Error(throwable.message)
        }.collectLatest {
            if (it != null) {
                _credentialState.value = ResultState.Success(it)
                mailRepository.setCredential(it)
            } else {
                _credentialState.value = ResultState.Error()
            }
        }
    }

    private fun updateBaseUrlState() = viewModelScope.launch(Dispatchers.IO) {
        dataStoreRepository.readString(R.string.key_url).catch { throwable ->
            _baseUrlState.value = ResultState.Error(throwable.message)
        }.collectLatest {
            if (it != null) {
                _baseUrlState.value = ResultState.Success(it)
                //Todo Update the logic here
            } else {
                _baseUrlState.value = ResultState.Error()
            }
        }
    }

    init {
        updateBaseUrlState()
        updateLoginTokenState()
        updateLoginCredentialState()
    }

}