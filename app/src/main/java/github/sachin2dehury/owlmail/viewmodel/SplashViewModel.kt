package github.sachin2dehury.owlmail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.repository.AuthRepository
import github.sachin2dehury.owlmail.repository.DataStoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _authToken = MutableStateFlow<String?>(null)
    val authToken = _authToken.asStateFlow()

    private val _authTokenExpireTime = MutableStateFlow<Long?>(null)
    val authTokenExpireTime = _authTokenExpireTime.asStateFlow()

    private val _baseUrl = MutableStateFlow<String?>(null)
    val baseUrl = _baseUrl.asStateFlow()

    private val _password = MutableStateFlow<String?>(null)
    val password = _password.asStateFlow()

    private val _username = MutableStateFlow<String?>(null)
    val username = _username.asStateFlow()

    fun updateAuthToken() {
        authRepository.authToken = _authToken.value
    }

    private fun updateInfo() = viewModelScope.launch(Dispatchers.IO) {
        dataStoreRepository.apply {
            _baseUrl.value = readString(R.string.key_url)
            _username.value = readString(R.string.key_username)
            _password.value = readString(R.string.key_password)
            _authToken.value = readString(R.string.key_auth_token)
            _authTokenExpireTime.value = readLong(R.string.key_auth_token_expire_time)
        }
    }

    init {
        updateInfo()
    }
}