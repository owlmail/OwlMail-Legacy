package github.sachin2dehury.owlmail.viewmodel

import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.api.ResultState
import github.sachin2dehury.owlmail.data.ZimbraSoap
import github.sachin2dehury.owlmail.data.auth.AuthRequest
import github.sachin2dehury.owlmail.data.search.SearchRequest
import github.sachin2dehury.owlmail.repository.AuthRepository
import github.sachin2dehury.owlmail.repository.DataStoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val authRepository: AuthRepository,
) : ViewModel() {

    var authRequest: AuthRequest? = null

    private val _loginState = MutableStateFlow<ResultState<ZimbraSoap>>(ResultState.Empty)
    val loginState = _loginState.asStateFlow()

    fun updateLoginState(baseURL: String) =
        viewModelScope.launch(Dispatchers.IO) {
            _loginState.value = ResultState.Loading
            _loginState.value = authRepository.makeAuthRequest(baseURL, authRequest)
        }

    fun saveLoginCredential(authToken: String, lifeTime: Long?) =
        viewModelScope.launch(Dispatchers.IO) {
            val expireTime =
                System.currentTimeMillis() - DateUtils.MINUTE_IN_MILLIS + (lifeTime ?: 0)
            dataStoreRepository.apply {
                saveData(R.string.key_username, authRequest?.account?.content)
                saveData(R.string.key_password, authRequest?.password?.content)
                saveData(R.string.key_auth_token, authToken)
                saveData(R.string.key_auth_token_expire_time, expireTime)
                saveData(R.string.key_should_sync, true)
            }
        }

    fun setAuthToken(authToken: String) {
        authRepository.setAuthToken(authToken)
    }

    fun makeSearch() = viewModelScope.launch(Dispatchers.IO) {
        val response = authRepository.makeSearchRequest(
            SearchRequest(
                offset = 0,
                limit = 100,
                query = "in:inbox",
            )
        )

        if (response is ResultState.Success) {
            Log.w("Sachin", "Response : ${response.value?.body?.searchResponse?.conversations}")
        }
    }

    fun resetLoginState() {
        authRepository.resetLogin()
        dataStoreRepository.resetLogin()
    }
}