package github.sachin2dehury.owlmail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.api.ResultState
import github.sachin2dehury.owlmail.datamodel.Items
import github.sachin2dehury.owlmail.repository.AuthRepository
import github.sachin2dehury.owlmail.repository.DataStoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _loginState by lazy { MutableStateFlow<ResultState<Items>>(ResultState.Loading) }
    val loginState: StateFlow<ResultState<Items>> by lazy { _loginState }

    fun updateLoginState(baseURL: String, credential: String) =
        viewModelScope.launch(Dispatchers.IO) {
            _loginState.value = ResultState.Loading
            authRepository.setCredential(credential)
            _loginState.value = authRepository.attemptLogin(baseURL)
        }

    fun saveLoginCredential() = viewModelScope.launch(Dispatchers.IO) {
        dataStoreRepository.apply {
            saveString(R.string.key_credential, authRepository.getCredential())
            saveString(R.string.key_token, authRepository.getToken())
            saveBoolean(R.string.key_should_sync, true)
        }
    }

    fun resetLoginState() = viewModelScope.launch(Dispatchers.IO) {
        dataStoreRepository.resetLogin()
        authRepository.resetLogin()
        _loginState.value = ResultState.Loading
    }
}