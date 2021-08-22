package github.sachin2dehury.owlmail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.api.BASE_URL
import github.sachin2dehury.owlmail.api.ResultState
import github.sachin2dehury.owlmail.datamodel.Items
import github.sachin2dehury.owlmail.repository.DataStoreRepository
import github.sachin2dehury.owlmail.repository.MailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val mailRepository: MailRepository,
) : ViewModel() {

    private val _loginState by lazy { MutableStateFlow<ResultState<Items>>(ResultState.Loading) }
    val loginState: StateFlow<ResultState<Items>> by lazy { _loginState }

    lateinit var credential: String

    fun updateLoginState() = viewModelScope.launch(Dispatchers.IO) {
        if (::credential.isInitialized) {
            _loginState.value = ResultState.Loading
            mailRepository.setCredential(credential)
            _loginState.value = mailRepository.attemptLogin()
        }
    }

    fun saveLoginCredential() = viewModelScope.launch(Dispatchers.IO) {
        if (::credential.isInitialized) {
            dataStoreRepository.apply {
                saveString(R.string.key_credential, credential)
                saveString(R.string.key_token, mailRepository.getToken())
                saveString(R.string.key_url, BASE_URL)
                saveBoolean(R.string.key_should_sync, true)
            }
        }
    }

    fun resetLoginState() = viewModelScope.launch(Dispatchers.IO) {
        dataStoreRepository.resetLogin()
        mailRepository.resetLogin()
        _loginState.value = ResultState.Loading
    }
}