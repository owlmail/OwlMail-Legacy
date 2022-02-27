package github.sachin2dehury.owlmail.viewmodel

import android.text.format.DateUtils
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.api.ResultState
import github.sachin2dehury.owlmail.data.AuthDetails
import github.sachin2dehury.owlmail.data.SessionDetails
import github.sachin2dehury.owlmail.repository.AuthRepository
import github.sachin2dehury.owlmail.repository.DataStoreRepository
import github.sachin2dehury.owlmail.utils.mapToResultState
import kotlinx.coroutines.CoroutineScope
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

    private val _sessionDetails = MutableStateFlow<ResultState<SessionDetails>>(ResultState.Empty)
    val sessionDetails = _sessionDetails.asStateFlow()

    fun makeAuthRequest(sessionDetails: SessionDetails) = viewModelScope.launch(Dispatchers.IO) {
        when (
            val response =
                authRepository.makeAuthRequest(sessionDetails.userDetails).mapToResultState()
        ) {
            is ResultState.Success -> {
                var details = sessionDetails
                response.value?.body?.authResponse?.let {
                    val authTokenExpireTime =
                        System.currentTimeMillis() - DateUtils.DAY_IN_MILLIS + (
                            it.lifetime
                                ?: 0
                            )
                    val authToken = it.authToken?.firstOrNull()?.content
                    val authDetails = AuthDetails(authToken, authTokenExpireTime)
                    details = sessionDetails.copy(authDetails = authDetails)
                }
                _sessionDetails.value = ResultState.Success(details)
            }
            is ResultState.Error ->
                _sessionDetails.value =
                    ResultState.Error(response.error, sessionDetails)
            else -> _sessionDetails.value = ResultState.Empty
        }
    }

    fun saveLoginCredential(sessionDetails: SessionDetails?) {
        authRepository.setAuthToken(sessionDetails?.authDetails?.authToken)
        CoroutineScope(Dispatchers.IO).launch {
            dataStoreRepository.apply {
                saveData(R.string.key_username, sessionDetails?.userDetails?.username)
                saveData(R.string.key_password, sessionDetails?.userDetails?.password)
                saveData(R.string.key_auth_token, sessionDetails?.authDetails?.authToken)
                saveData(
                    R.string.key_auth_token_expire_time,
                    sessionDetails?.authDetails?.authTokenExpireTime
                )
                saveData(R.string.key_url, sessionDetails?.userDetails?.baseUrl)
                saveData(R.string.key_should_sync, true)
            }
        }
    }
}
