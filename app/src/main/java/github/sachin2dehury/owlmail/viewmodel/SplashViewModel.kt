package github.sachin2dehury.owlmail.viewmodel

import android.text.format.DateUtils
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.api.ResultState
import github.sachin2dehury.owlmail.data.AuthDetails
import github.sachin2dehury.owlmail.data.SessionDetails
import github.sachin2dehury.owlmail.data.UserDetails
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
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    private val _sessionDetails = MutableStateFlow<ResultState<SessionDetails>>(ResultState.Empty)
    val sessionDetails = _sessionDetails.asStateFlow()

    fun getUserDetails() = viewModelScope.launch(Dispatchers.IO) {
        dataStoreRepository.apply {
            val userDetails = UserDetails(
                readString(R.string.key_username),
                readString(R.string.key_password),
                readString(R.string.key_url),
            )
            val authDetails = AuthDetails(
                readString(R.string.key_auth_token),
                readLong(R.string.key_auth_token_expire_time),
            )
            refreshAuthToken(SessionDetails(userDetails, authDetails))
        }
    }

    private suspend fun refreshAuthToken(sessionDetails: SessionDetails) = when (val response =
        authRepository.makeAuthRequest(sessionDetails.userDetails).mapToResultState()) {
        is ResultState.Success -> {
            var details = sessionDetails
            response.value?.body?.authResponse?.let {
                val authTokenExpireTime =
                    System.currentTimeMillis() - DateUtils.DAY_IN_MILLIS + (it.lifetime
                        ?: 0)
                val authToken = it.authToken?.firstOrNull()?.content
                val authDetails = AuthDetails(authToken, authTokenExpireTime)
                details = sessionDetails.copy(authDetails = authDetails)
            }
            _sessionDetails.value = ResultState.Success(details)
        }
        is ResultState.Error -> _sessionDetails.value =
            ResultState.Error(response.error, sessionDetails)
        else -> _sessionDetails.value = ResultState.Empty
    }

    fun saveAuthDetails(authDetails: AuthDetails?) {
        authRepository.setAuthToken(authDetails?.authToken)
        CoroutineScope(Dispatchers.IO).launch {
            dataStoreRepository.apply {
                saveData(R.string.key_auth_token, authDetails?.authToken)
                saveData(R.string.key_auth_token_expire_time, authDetails?.authTokenExpireTime)
            }
        }
    }
}